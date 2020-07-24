import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css']
})
export class LoginViewComponent implements OnInit {
  myForm: FormGroup;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth')
    })
  };

  constructor(private http: HttpClient,
              private fb: FormBuilder,
              private cookieService: CookieService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.myForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  submit() {
    this.submitForm();
  }

  submitForm = () => {
    const auth: string =
      'Basic ' +
      btoa(
        this.myForm.controls.username.value +
        ':' +
        this.myForm.controls.password.value
      );
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: auth,
      }),
    };
    this.http
      .get<boolean>(
        'https://wws-edge-service.herokuapp.com/users/is-admin-user',
        httpOptions
      )
      .subscribe((resp) => {
        if (resp) {
          this.cookieService.set('auth', auth);
          localStorage.setItem('username', this.myForm.controls.username.value);
          this.router.navigate(['/home'], { relativeTo: this.route });
        } else {
          this.myForm.controls.username.setErrors({ incorrect: true });
          this.myForm.controls.password.setErrors({ incorrect: true });
        }
      });
  }
  
  goToNewUser(){
    this.router.navigate(['/create-user'], { relativeTo: this.route });
  }

  get username() {
    return this.myForm.get('username');
  }

  get password() {
    return this.myForm.get('password');
  }
}
