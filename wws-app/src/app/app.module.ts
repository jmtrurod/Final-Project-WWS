import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CityViewComponent } from './city-view/city-view.component';
import { PostCardComponent } from './post-card/post-card.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProfileHeaderComponent } from './profile-header/profile-header.component';
import { CreatePostFormComponent } from './create-post-form/create-post-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { LoginViewComponent } from './login-view/login-view.component';
import { ProfileViewComponent } from './profile-view/profile-view.component';
import { AllCitiesComponent } from './all-cities/all-cities.component';


@NgModule({
  declarations: [
    AppComponent,
    CityViewComponent,
    PostCardComponent,
    NavbarComponent,
    ProfileHeaderComponent,
    CreatePostFormComponent,
    LoginViewComponent,
    ProfileViewComponent,
    AllCitiesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule
  ],
  providers: [ CookieService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
