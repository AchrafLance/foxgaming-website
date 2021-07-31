import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { TopHeaderComponent } from './header/top-header/top-header.component';
import { NavbarComponent } from './header/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TeamsComponent } from './header/navbar/teams/teams.component';
import { ShopComponent } from "./main/shop/shop.component";
import { MediaComponent } from './header/navbar/media/media.component';
import { CompanyComponent } from './header/navbar/company/company.component';
import { FooterComponent } from './footer/footer.component';
import { MainComponent } from './main/main.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import {AppRoutingModule } from './app-routing-module';
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { MdbCheckboxModule } from 'mdb-angular-ui-kit/checkbox';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { MdbPopoverModule } from 'mdb-angular-ui-kit/popover';
import { MdbRadioModule } from 'mdb-angular-ui-kit/radio';
import { MdbRangeModule } from 'mdb-angular-ui-kit/range';
import { MdbRippleModule } from 'mdb-angular-ui-kit/ripple';
import { MdbScrollspyModule } from 'mdb-angular-ui-kit/scrollspy';
import { MdbTabsModule } from 'mdb-angular-ui-kit/tabs';
import { MdbTooltipModule } from 'mdb-angular-ui-kit/tooltip';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import {IvyCarouselModule} from 'angular-responsive-carousel';
import { CardComponent } from './main/shop/card/card.component';
import { ProductDetailsComponent } from './main/shop/product-details/product-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TopHeaderComponent,
    NavbarComponent,
    TeamsComponent,
    ShopComponent,
    MediaComponent,
    CompanyComponent,
    FooterComponent,
    MainComponent,
    LoginComponent,
    RegisterComponent,
    ForgotPasswordComponent,
    CardComponent,
    ProductDetailsComponent,
  ],
  imports: [
    AppRoutingModule, 
    BrowserModule,
    BrowserAnimationsModule,
    MdbCarouselModule,
    MdbCheckboxModule,
    MdbCollapseModule,
    MdbDropdownModule,
    MdbFormsModule,
    MdbModalModule,
    MdbPopoverModule,
    MdbRadioModule,
    MdbRangeModule,
    MdbRippleModule,
    MdbScrollspyModule,
    MdbTabsModule,
    MdbTooltipModule,
    MdbValidationModule,
    IvyCarouselModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
