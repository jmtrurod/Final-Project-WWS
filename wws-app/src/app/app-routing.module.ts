import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CityViewComponent } from './city-view/city-view.component';
import { LoginViewComponent } from './login-view/login-view.component';
import { ProfileViewComponent } from './profile-view/profile-view.component';
import { AllCitiesComponent } from './all-cities/all-cities.component';


const routes: Routes = [
  {
    path: 'home',
    component: AllCitiesComponent
  },
  {
    path: 'cities',
    component: CityViewComponent
  },
  {
    path: 'profile',
    component: ProfileViewComponent
  },
  {
    path: 'login',
    component: LoginViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
