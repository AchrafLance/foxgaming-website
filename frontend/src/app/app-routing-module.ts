import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; // CLI imports router
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { ShopComponent } from './main/shop/shop.component';
import { MainComponent } from './main/main.component';
import { ProductDetailsComponent } from './main/shop/product-details/product-details.component';
import { CartComponent } from './main/cart/cart.component';
import { CartResolver } from './services/resolvers/cart-resolver';
import { AuthGuard } from './_guards/auth-guard';
import { NewsComponent } from './main/news/news.component';
import { MediaComponent } from './main/media/media.component';
import { AboutComponent } from './main/about/about.component';
import { SquadsComponent } from './main/squads/squads.component';
import { ChangePasswordComponent} from './authentication/change-password/change-password.component'


const routes: Routes = [
    { path: "", component: MainComponent, resolve: {cartItems: CartResolver}},
    { path: "home", component: MainComponent, resolve: {cartItems: CartResolver}},
    { path : "login", component: LoginComponent}, 
    { path: "register", component: RegisterComponent},
    { path: "forgot-password", component: ForgotPasswordComponent},
    { path: "shop", component: ShopComponent},
    { path: "shop/product/:id", component: ProductDetailsComponent},
    { path: "cart", component: CartComponent, canActivate:[AuthGuard]},
    { path: "squads", component: SquadsComponent},
    { path: "news", component: NewsComponent},
    { path: "media", component: MediaComponent},
    { path: "about", component: AboutComponent},
    {path:"changePassword/:token", component: ChangePasswordComponent}

]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }