import { Component, OnInit, Input } from '@angular/core';
import { PostAndUser } from '../Model/postAndUser.model';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Post } from '../Model/post.model';
import { City } from '../Model/city.model';
import { User } from '../Model/user.model';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute, private cookieService: CookieService) {}

  ngOnInit(): void {
    if (!(this.cookieService.get('auth') && localStorage.getItem('username'))){
      this.router.navigate(['/login'], { relativeTo: this.route });
    }
    console.log('a');
    console.log(this.route.snapshot.queryParams);
    console.log('a');
    console.log(this.cityName);
    this.route.queryParams.subscribe( (params) => this.cityName = params.cityName );
    console.log(this.cityName);

    this.http.get<City>('https://wws-edge-service.herokuapp.com/cities/' + this.cityName, this.httpOptions).subscribe( city => {
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
    this.http.get<Post[]>('https://wws-edge-service.herokuapp.com/posts/city/theme?cityId=' + this.city.id + '&theme=' + theme, this.httpOptions)
    .subscribe(posts => {
      this.posts = posts;
      this.posts.forEach(p => {
        let auxPaU = new PostAndUser();
        this.http.get<User>('https://wws-edge-service.herokuapp.com/users/' + p.username, this.httpOptions).subscribe( u => {
          auxPaU.user = u;
          auxPaU.post = p;
          this.postsAndUsers.push(auxPaU);
        });
      });
    });
  }

  updateListPost(id: number){
    this.postsAndUsers = this.postsAndUsers.filter( p => p.post.id !== id );
  }

  allPosts(){
    this.postsAndUsers = [];
    this.http.get<Post[]>('https://wws-edge-service.herokuapp.com/posts/city/' + this.cityName, this.httpOptions).subscribe(posts => {
        console.log(posts);
        this.posts = posts;
        this.posts.forEach(p => {
          let auxPaU = new PostAndUser();
          this.http.get<User>('https://wws-edge-service.herokuapp.com/users/' + p.username, this.httpOptions).subscribe( u => {
          auxPaU.user = u;
          auxPaU.post = p;
          this.postsAndUsers.push(auxPaU);
        });
      });
    });
  }
}
