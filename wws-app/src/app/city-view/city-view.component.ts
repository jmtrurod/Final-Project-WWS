import { Component, OnInit, Input } from '@angular/core';
import { PostAndUser } from '../Model/postAndUser.model';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Post } from '../Model/post.model';
import { City } from '../Model/city.model';
import { User } from '../Model/user.model';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-city-view',
  templateUrl: './city-view.component.html',
  styleUrls: ['./city-view.component.css']
})
export class CityViewComponent implements OnInit {
  posts: Post[] = [];
  postsAndUsers: PostAndUser[] = [];
  city: City;
  cityName: string;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth'),
    }),
  };

  constructor(private http: HttpClient, private route: ActivatedRoute, private cookieService: CookieService) {}

  ngOnInit(): void {

    console.log(this.route.snapshot.queryParams);
    console.log(this.cityName);
    this.route.queryParams.subscribe( (params) => this.cityName = params.cityName );
    console.log(this.cityName);

    this.http.get<City>('http://localhost:8080/cities/' + this.cityName, this.httpOptions).subscribe( city => {
      this.city = city;
      console.log(this.city);
      this.allPosts();
    });
  }

  filterByTheme(theme: string){
    if (theme === 'ALL'){
      this.allPosts();
      return;
    }
    this.postsAndUsers = [];
    this.http.get<Post[]>('http://localhost:8080/posts/city/theme?cityId=' + this.city.id + '&theme=' + theme, this.httpOptions)
    .subscribe(posts => {
      this.posts = posts;
      this.posts.forEach(p => {
        let auxPaU = new PostAndUser();
        this.http.get<User>('http://localhost:8080/users/' + p.username, this.httpOptions).subscribe( u => {
          auxPaU.user = u;
          auxPaU.post = p;
          this.postsAndUsers.push(auxPaU);
        });
      });
    });
  }

  allPosts(){
    this.postsAndUsers = [];
    this.http.get<Post[]>('http://localhost:8080/posts/city/' + this.cityName, this.httpOptions).subscribe(posts => {
        console.log(posts);
        this.posts = posts;
        this.posts.forEach(p => {
          let auxPaU = new PostAndUser();
          this.http.get<User>('http://localhost:8080/users/' + p.username, this.httpOptions).subscribe( u => {
          auxPaU.user = u;
          auxPaU.post = p;
          this.postsAndUsers.push(auxPaU);
        });
      });
    });
  }
}
