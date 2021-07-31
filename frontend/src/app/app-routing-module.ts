import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { ShopComponent } from './main/shop/shop.component';
import { MainComponent } from './main/main.component';
import { ProductDetailsComponent } from './main/shop/product-details/product-details.component';



const routes: Routes = [
    { path: 'home', component: MainComponent },
    { path : 'login', component: LoginComponent}, 
    { path: "register", component: RegisterComponent},
    { path: "forgot-password", component: ForgotPasswordComponent},
    { path: "shop", component: ShopComponent},
    { path: "shop/product/:id", component: ProductDetailsComponent},
    { path: "", component: MainComponent}
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }