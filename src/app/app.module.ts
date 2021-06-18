import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { TopHeaderComponent } from './header/top-header/top-header.component';
import { NavbarComponent } from './header/navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TeamsComponent } from './header/navbar/teams/teams.component';
import { ShopComponent } from './header/navbar/shop/shop.component';
import { MediaComponent } from './header/navbar/media/media.component';
import { CompanyComponent } from './header/navbar/company/company.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TopHeaderComponent,
    NavbarComponent,
    TeamsComponent,
    ShopComponent,
    MediaComponent,
    CompanyComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
