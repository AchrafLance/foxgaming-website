import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/LoginRequest';
import { LoginResponse } from 'src/app/models/LoginResponse';
import { AuthService } from 'src/app/services/auth-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usernameOrEmail:string; 
  password: string; 

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    let loginRequest = new LoginRequest(); 
    loginRequest.password = this.password; 
    loginRequest.usernameOrEmail = this.usernameOrEmail; 

    this.authService.login(loginRequest).subscribe(() => {
        this.router.navigate(['/home']) 
      })  
  }

}
