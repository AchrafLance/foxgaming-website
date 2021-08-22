package com.fox.website.api;

import com.fox.website.dtos.ResetPasswordDTO;
import com.fox.website.dtos.UpdatePasswordDTO;
import com.fox.website.exceptions.AppException;
import com.fox.website.models.*;
import com.fox.website.payload.ApiResponse;
import com.fox.website.payload.JwtAuthenticationResponse;
import com.fox.website.payload.LoginRequest;
import com.fox.website.payload.SignUpRequest;
import com.fox.website.repositories.*;
import com.fox.website.security.JwtTokenProvider;
import com.fox.website.services.SecurityService;
import com.fox.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    SecurityService securityService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        Cart savedCart = cartRepository.save(new Cart());
        Wishlist savedWishlist = wishlistRepository.save(new Wishlist());

        user.setCart(savedCart);
        user.setWishlist(savedWishlist);

        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

    }

    @PostMapping("/resetPassword")
    public void resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        User user = userService.findUserByEmail(resetPasswordDTO.email);
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        userService.sendResetTokenEmail(token, user);
    }

    @GetMapping("/changePassword")
    public ModelAndView showChangePasswordPage(@RequestParam("token") String token){
        String result = securityService.validatePasswordResetToken(token);
        if(result != null){
            return new ModelAndView("redirect:" + "http://localhost:4200/login");
        }
        else{
            return new ModelAndView("redirect:"+ "http://localhost:4200/changePassword/" + token);
        }
    }

    @PostMapping("/updatePassword")
    public void savePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        String result = securityService.validatePasswordResetToken(updatePasswordDTO.token);
        if(result == null){
           Optional<User> user =  userService.getUserByPasswordResetToken(updatePasswordDTO.token);
           if(user.isPresent()){
               userService.changeUserPassword(user.get(), updatePasswordDTO.password);
               passwordResetTokenRepository.deleteById(
                       passwordResetTokenRepository.findByToken(updatePasswordDTO.token).getId()
               );

           }
        }
    }

}
