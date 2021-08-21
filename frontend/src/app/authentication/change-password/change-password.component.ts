import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth-service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  token:string; 

  constructor(private router:Router, private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private toasterService: ToastrService){ }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(data => {
      this.token = data.token; 
    })

  }

  updatePassword(form: NgForm){
    if(form.value.password === form.value.RePassword){
      this.authService.updatePassword(form.value.password, this.token).subscribe(()=>{
        this.router.navigate(["/home"]); 
        this.toasterService.success("password updated!")
      })
    }
    else{
      this.toasterService.error("passwords are diffrent", "Error");
    }
  }

}
