import { Component, OnInit, Input } from '@angular/core';
import { Post } from '../Model/post.model';
import { PostAndUser } from '../Model/postAndUser.model';
import { User } from '../Model/user.model';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-myprofile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.css']
})
export class ProfileViewComponent implements OnInit {
  @Input()
  username: string;

  posts: Post[] = [];
  postsAndUsers: PostAndUser[] = [];
  user: User;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth'),
    }),
  };

  constructor(private http: HttpClient,
              private cookieService: CookieService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (!this.username){
      this.username = localStorage.getItem('username');
    }
    this.http.get<User>('http://localhost:8080/users/' + this.username, this.httpOptions).subscribe( user => {
      this.user = user;
      this.allPosts();
    });
  }

  updateList(postEmiter: Post){
    let aux2PaU = new PostAndUser();
    aux2PaU.user = this.user;
    aux2PaU.post = postEmiter;
    this.postsAndUsers.push(aux2PaU);
  }

  filterByTheme(theme: string){
    if (theme === 'ALL'){
      this.allPosts();
      return;
    }
    this.postsAndUsers = [];
    this.http.get<Post[]>('http://localhost:8080/posts/person/theme?username=' + this.user.username + '&theme=' + theme,
     this.httpOptions).subscribe(posts => {
        this.posts = posts;
        this.posts.forEach(p => {
          let auxPaU = new PostAndUser();
          auxPaU.user = this.user;
          auxPaU.post = p;
          this.postsAndUsers.push(auxPaU);
        });
      });
  }

  allPosts(){
    this.postsAndUsers = [];
    this.http.get<Post[]>('http://localhost:8080/posts/person/' + this.username, this.httpOptions).subscribe(posts => {
      this.posts = posts;
      this.posts.forEach(p => {
        let auxPaU = new PostAndUser();
        auxPaU.user = this.user;
        auxPaU.post = p;
        this.postsAndUsers.push(auxPaU);
      });
    });
  }

  toCity(){
    this.router.navigate(['/cities'], {queryParams: {cityName: 'Madrid-España'}, relativeTo: this.route });
  }
}
