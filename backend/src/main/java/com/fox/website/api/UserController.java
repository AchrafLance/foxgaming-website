package com.fox.website.api;

import com.fox.website.models.User;
import com.fox.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.register(user));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<User>> users(){
        try{
            return ResponseEntity.ok(userService.findAllUsers());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
