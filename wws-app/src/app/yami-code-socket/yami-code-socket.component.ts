import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { environment } from '../../environments/environment';
import { SocketService } from '../services/socket.service';
import { ToastrService } from 'ngx-toastr';
import { Message } from '../Model/message';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../Model/user.model';
import { CookieService } from 'ngx-cookie-service';
import { MessageAndUser } from '../Model/messageAndUser.model';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-yami-code-socket',
  templateUrl: './yami-code-socket.component.html',
  styleUrls: ['./yami-code-socket.component.css']
})
export class YamiCodeSocketComponent implements OnInit {
  allUsers: User[] = [];
  serverUrl = environment.url + 'socket';
  isLoaded = false;
  isCustomSocketOpened = false;
  stompClient;
  form: FormGroup;
  userForm: FormGroup;
  messages: Message[] = [];
  messagesAndUsers: MessageAndUser[] = [];
  canSend = false;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth'),
    }),
  };

  constructor(private socketService: SocketService,
              private toastr: ToastrService,
              private http: HttpClient,
              private cookieService: CookieService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    if (!(this.cookieService.get('auth') && localStorage.getItem('username'))){
      this.router.navigate(['/login'], { relativeTo: this.route });
    }
    this.http.get<User[]>('https://wws-edge-service.herokuapp.com/users', this.httpOptions)
    .subscribe(users => {
      users.forEach(u => {
        if (u.username !== localStorage.getItem('username')){
          this.allUsers.push(u);
        }
      });
    });

    this.form = new FormGroup({
      message: new FormControl(null, [Validators.required])
    });
    this.userForm = new FormGroup({
      toId: new FormControl(null, [Validators.required])
    });
    this.initializeWebSocketConnection();
  }

  sendMessageUsingSocket() {
    this.http.get<boolean>('https://wws-edge-service.herokuapp.com/users/is-allowed-user?username=' + localStorage.getItem('username'), this.httpOptions)
    .subscribe( b => this.canSend = b );

    if (this.form.valid && this.canSend) {
      let message: Message = { message: this.form.value.message, fromId: localStorage.getItem('username'), toId: this.userForm.value.toId };
      this.stompClient.send('/socket-subscriber/send/message', {}, JSON.stringify(message));
      this.form.reset();
    }

    this.canSend = false;
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.isLoaded = true;
      that.openSocket();
      that.openGlobalSocket();
    });
  }

  openGlobalSocket() {
    this.stompClient.subscribe('/socket-publisher', (message) => {
      this.handleResult(message);
    });
  }

  openSocket() {
    if (this.isLoaded) {
      this.isCustomSocketOpened = true;
      this.stompClient.subscribe('/socket-publisher/' + localStorage.getItem('username'), (message) => {
        this.handleResult(message);
      });
    }
  }

  handleResult(message){
    if (message.body) {
      let messageResult: Message = JSON.parse(message.body);
      console.log(messageResult);
      this.messages.push(messageResult);
      let mau = new MessageAndUser();
      mau.message = messageResult;
      mau.toUserId = messageResult.toId;
      this.http.get<User>('https://wws-edge-service.herokuapp.com/users/' + messageResult.fromId, this.httpOptions)
      .subscribe( user => {
        user.convertedPic = 'data:image/jpeg;base64,' + user.pic;
        mau.user = user;
        this.messagesAndUsers.push(mau);
        this.toastr.success('new message recieved', null, {
        timeOut: 3000
      });
      });
    }
  }

}
