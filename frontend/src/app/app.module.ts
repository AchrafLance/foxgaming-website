import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
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
import {CarouselModule } from 'ngx-owl-carousel-o';
import { CardComponent } from './main/shop/card/card.component';
import { ProductDetailsComponent } from './main/shop/product-details/product-details.component';
import { CartComponent } from './main/cart/cart.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './_interceptors/jwt-interceptor';
import { ErrorInterceptor } from './_interceptors/error-interceptor';
import { SidebarComponent } from './header/sidebar/sidebar.component';
import { ToastrModule } from 'ngx-toastr';
import { NgxSpinnerModule } from "ngx-spinner";

import { NewsComponent } from './main/news/news.component';
import { MediaComponent } from './main/media/media.component';
import { AboutComponent } from './main/about/about.component';
import { SquadsComponent } from './main/squads/squads.component';
import { ChangePasswordComponent } from './authentication/change-password/change-password.component';

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
    ChangePasswordComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    CarouselModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right'
    }),
    NgxSpinnerModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],


  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
