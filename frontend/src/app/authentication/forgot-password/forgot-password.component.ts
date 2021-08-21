import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth-service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor(private router:Router, private authService: AuthService, 
    private toastrService: ToastrService,
    private spinnerService:NgxSpinnerService) { }

  ngOnInit(): void {
  }

  resetPassword(form: NgForm){
    let email = form.value.email; 
    this.spinnerService.show()
    this.authService.resetPassowrd(email).subscribe(() => {
      this.spinnerService.hide(); 
      this.toastrService.success("check your email to reset your password"); 
      this.router.navigate(["/home"]);
      })

  }

}
