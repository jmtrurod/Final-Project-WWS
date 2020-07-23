import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PostAndUser } from '../Model/postAndUser.model';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {
  @Input()
  postAndUser: PostAndUser;

  @Input()
  service: string;

  @Output()
  postEmiter: EventEmitter<number> = new EventEmitter<number>();

  cardClass: string;
  convertedImage: any;
  displayCity = false;
  allowLikes = true;
  username: string;
  allowedUserOrAdmin = false;

  httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth')
    }),
  };

  constructor(private http: HttpClient,
              private cookieService: CookieService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username');

    console.log(this.postAndUser);
    if (this.postAndUser.post.votingUsers.indexOf(this.username) !== -1){
      this.allowLikes = false;
    }
    if (this.service === 'profile'){
      this.displayCity = true;
    }
    this.cardClass = 'card ' + this.postAndUser.post.theme.toLocaleLowerCase() + '-card';
    this.convertedImage = 'data:image/jpeg;base64,' + this.postAndUser.user.pic;

    this.http.get<boolean>('http://localhost:8080/users/is-admin-allowed-user?username=' + this.postAndUser.post.username, this.httpOptions)
    .subscribe( b => this.allowedUserOrAdmin = b );
  }

  incrementKarma(){
    const params = new HttpParams({
      fromObject: {
        username: localStorage.getItem('username')
      }
    });
    this.http.
    put<void>('http://localhost:8080/posts/increment/' + this.postAndUser.post.id + '?username=' + this.username,
    params,
    this.httpOptions).subscribe();
    this.postAndUser.post.karma ++;
    this.allowLikes = false;
  }


  decrementKarma(){
    const params = new HttpParams({
      fromObject: {
        username: localStorage.getItem('username')
      }
    });
    this.http.
    put<void>('http://localhost:8080/posts/decrement/' + this.postAndUser.post.id + '?username=' + this.username,
    params,
    this.httpOptions).subscribe();
    this.postAndUser.post.karma --;
    this.allowLikes = false;
}

  goToProfile(username2: string){
    this.router.navigate(['/profile'], {queryParams:{ username: username2 }, relativeTo: this.route});
  }

  deletePost(){
    this.http.delete<void>('http://localhost:8080/posts/' + this.postAndUser.post.id
    + '?username=' + localStorage.getItem('username'), this.httpOptions).subscribe();
    this.postEmiter.emit(this.postAndUser.post.id);
  }

}
