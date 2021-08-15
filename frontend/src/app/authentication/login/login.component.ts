import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/LoginRequest';
import { LoginResponse } from 'src/app/models/LoginResponse';
import { AuthService } from 'src/app/services/auth-service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(form: NgForm){
    let loginRequest = new LoginRequest(); 
    loginRequest.password = form.value.password; 
    loginRequest.usernameOrEmail = form.value.usernameOrEmail; 

    this.authService.login(loginRequest).subscribe((data) => {
      if(data){
        this.router.navigate(['/home']) 
      }
      })  
  }

}
