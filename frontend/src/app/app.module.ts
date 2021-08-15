import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TopHeaderComponent } from './header/top-header/top-header.component';
import { NavbarComponent } from './header/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TeamsComponent } from './header/navbar/teams/teams.component';
import { ShopComponent } from "./main/shop/shop.component";
import { FooterComponent } from './footer/footer.component';
import { MainComponent } from './main/main.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import {AppRoutingModule } from './app-routing-module';
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';
import {IvyCarouselModule} from 'angular-responsive-carousel';
import { CardComponent } from './main/shop/card/card.component';
import { ProductDetailsComponent } from './main/shop/product-details/product-details.component';
import { CartComponent } from './main/cart/cart.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './_interceptors/jwt-interceptor';
import { ErrorInterceptor } from './_interceptors/error-interceptor';
import { SidebarComponent } from './header/sidebar/sidebar.component';
import { ToastrModule } from 'ngx-toastr';
import { NewsComponent } from './main/news/news.component';
import { MediaComponent } from './main/media/media.component';
import { AboutComponent } from './main/about/about.component';
import { SquadsComponent } from './main/squads/squads.component';

@NgModule({
  declarations: [
    AppComponent,
    TopHeaderComponent,
    NavbarComponent,
    TeamsComponent,
    ShopComponent,
    FooterComponent,
    MainComponent,
    LoginComponent,
    RegisterComponent,
    ForgotPasswordComponent,
    CardComponent,
    ProductDetailsComponent,
    CartComponent,
    SidebarComponent,
    NewsComponent,
    MediaComponent,
    AboutComponent,
    SquadsComponent,
  ],
  imports: [
    AppRoutingModule, 
    BrowserModule,
    BrowserAnimationsModule,
    IvyCarouselModule,
    FormsModule,
    HttpClientModule,   
    ToastrModule.forRoot({
      timeOut: 2500,
      positionClass: 'toast-top-right'
    }) 
  ],
  
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
