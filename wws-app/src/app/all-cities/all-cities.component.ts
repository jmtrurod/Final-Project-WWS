import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { City } from '../Model/city.model';

@Component({
  selector: 'app-all-cities',
  templateUrl: './all-cities.component.html',
  styleUrls: ['./all-cities.component.css']
})
export class AllCitiesComponent implements OnInit {

  listCities: City[];

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth'),
    }),
  };

  constructor(private http: HttpClient, private cookieService: CookieService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (!(this.cookieService.get('auth') && localStorage.getItem('username'))){
      this.router.navigate(['/login'], { relativeTo: this.route });
    }

    this.http.get<City[]>('https://wws-edge-service.herokuapp.com/cities-obj', this.httpOptions).subscribe(
      cities => this.listCities = cities
    );
  }

  goToCity(city: string){
    this.router.navigate(['/cities'], {queryParams: {cityName: city}, relativeTo: this.route });
  }

}
