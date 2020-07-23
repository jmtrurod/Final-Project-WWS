import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @Input()
  mainName: string;

  user: string = localStorage.getItem('username');

  constructor(private router: Router, private route: ActivatedRoute ) {}

  ngOnInit(): void {
  }

  goHome(){
      this.router.navigate(['/home'], {relativeTo: this.route});
  }

  goMyProfile(){
    this.router.navigate(['/profile'], {relativeTo: this.route});
  }

  goChat(){
    this.router.navigate(['/chat'], {relativeTo: this.route});
  }

  goCreateCity(){
    this.router.navigate(['/create-city'], {relativeTo: this.route});
  }

}
