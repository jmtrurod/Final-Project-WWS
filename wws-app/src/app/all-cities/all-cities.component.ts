import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-all-cities',
  templateUrl: './all-cities.component.html',
  styleUrls: ['./all-cities.component.css']
})
export class AllCitiesComponent implements OnInit {

  cities: string[];

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
    this.http.get<string[]>('http://localhost:8080/cities', this.httpOptions).subscribe(
      cities => this.cities = cities
    );
  }

  goToCity(city: string){
    this.router.navigate(['/cities'], {queryParams: {cityName: city}, relativeTo: this.route });
  }

}
