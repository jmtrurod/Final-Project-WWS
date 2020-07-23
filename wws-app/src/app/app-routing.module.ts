import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CityViewComponent } from './city-view/city-view.component';
import { LoginViewComponent } from './login-view/login-view.component';
import { ProfileViewComponent } from './profile-view/profile-view.component';
import { AllCitiesComponent } from './all-cities/all-cities.component';
import { YamiCodeSocketComponent } from './yami-code-socket/yami-code-socket.component';
import { ErrorViewComponent } from './error-view/error-view.component';
import { CreateUserViewComponent } from './create-user-view/create-user-view.component';
import { CreateCityViewComponent } from './create-city-view/create-city-view.component';


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
  },
  {
    path: 'chat',
    component: YamiCodeSocketComponent
  },
  {
    path: 'create-user',
    component: CreateUserViewComponent
  },
  {
    path: 'create-city',
    component: CreateCityViewComponent
  },
  {
    path: '**',
    redirectTo: 'home',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
