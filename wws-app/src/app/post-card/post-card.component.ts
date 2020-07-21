import { Component, OnInit, Input } from '@angular/core';
import { PostAndUser } from '../Model/postAndUser.model';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

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

  cardClass: string;
  convertedImage: any;
  displayCity = false;
  allowLikes = true;
  username: string;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth'),
    }),
  };

  constructor(private http: HttpClient, private cookieService: CookieService) {}

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

}
