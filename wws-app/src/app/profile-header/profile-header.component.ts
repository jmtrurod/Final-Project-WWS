import { Component, OnInit, Input } from '@angular/core';
import { User } from '../Model/user.model';

@Component({
  selector: 'app-profile-header',
  templateUrl: './profile-header.component.html',
  styleUrls: ['./profile-header.component.css']
})
export class ProfileHeaderComponent implements OnInit {
  @Input()
  user: User;

  convertedImage: any;

  constructor() { }

  ngOnInit(): void {
    this.convertedImage = 'data:image/jpeg;base64,' + this.user.pic;
  }

}
