import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/LoginRequest';
import { RegisterRequest } from 'src/app/models/RegisterRequest';
import { AuthService } from 'src/app/services/auth-service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  fullName: string; 
  username: string; 
  email: string; 
  password: string; 

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  register(form: NgForm){
    let registerRequest = new RegisterRequest(); 
    registerRequest.name = form.value.fullName; 
    registerRequest.username = form.value.username; 
    registerRequest.email = form.value.email; 
    registerRequest.password = form.value.password; 

    this.authService.register(registerRequest).subscribe(data => {
      if(data.success){
        let loginRequest = new LoginRequest(); 
        loginRequest.usernameOrEmail = this.username; 
        loginRequest.password = this.password; 
        this.authService.login(loginRequest).subscribe(() => {
          this.router.navigate(['/home']);
        })
      }
    })

  }

}
