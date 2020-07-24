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
  profileUser: string = localStorage.getItem('username');

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
    if (!(this.cookieService.get('auth') && localStorage.getItem('username'))){
      this.router.navigate(['/login'], { relativeTo: this.route });
    }
    this.username = this.route.snapshot.queryParamMap.get('username');
    if (!this.username){
      this.username = localStorage.getItem('username');
    }
    this.http.get<User>('https://wws-edge-service.herokuapp.com/users/' + this.username, this.httpOptions).subscribe( user => {
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

  updateListPost(id: number){
    this.postsAndUsers = this.postsAndUsers.filter( p => p.post.id !== id );
  }

  filterByTheme(theme: string){
    if (theme === 'ALL'){
      this.allPosts();
      return;
    }
    this.postsAndUsers = [];
    this.http.get<Post[]>('https://wws-edge-service.herokuapp.com/posts/person/theme?username=' + this.user.username + '&theme=' + theme,
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
    this.http.get<Post[]>('https://wws-edge-service.herokuapp.com/posts/person/' + this.username, this.httpOptions).subscribe(posts => {
      this.posts = posts;
      this.posts.forEach(p => {
        let auxPaU = new PostAndUser();
        auxPaU.user = this.user;
        auxPaU.post = p;
        this.postsAndUsers.push(auxPaU);
      });
    });
  }
}
