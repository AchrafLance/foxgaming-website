import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { MainComponent } from './main/main.component';



const routes: Routes = [
    { path: 'home', component: MainComponent },
    { path : 'login', component: LoginComponent}, 
    { path: "register", component: RegisterComponent},
    { path: "forgot-password", component: ForgotPasswordComponent},
    { path: " ", component: MainComponent}
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }