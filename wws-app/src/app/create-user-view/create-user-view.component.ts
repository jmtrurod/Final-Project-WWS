import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../Model/user.model';
import { SecurityUser } from '../Model/securityUser.model';
import { ImageModel } from '../Model/ImageModel';

@Component({
  selector: 'app-create-user-view',
  templateUrl: './create-user-view.component.html',
  styleUrls: ['./create-user-view.component.css']
})
export class CreateUserViewComponent implements OnInit {
  myForm: FormGroup;
  isValid = true;

  public selectedFile;
  public event1;
  imgURL: any;
  receivedImageData: ImageModel;
  base64Data: any;
  convertedImage: any;
  isOn = false;

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
      username: ['', [Validators.required, Validators.maxLength(15), Validators.minLength(6)]],
      name: ['', [Validators.required, Validators.maxLength(60), Validators.minLength(12)]],
      bio: ['', [Validators.required, Validators.maxLength(140), Validators.minLength(5)]],
      mail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]]
    });
    this.myForm.valueChanges.subscribe(() => console.log(this.imgURL));
  }

  submit() {
    console.log('0');
    let user = new SecurityUser();
    user.username = this.username.value;
    user.password = this.password.value;
    let firstAttempt = false;
    console.log('a');
    this.http.post<SecurityUser>('https://wws-edge-service.herokuapp.com/users-create', user).subscribe( () => {
      console.log('b');
      if (!firstAttempt) {
        firstAttempt = true;
        let newUser = new User();
        newUser.bio = this.bio.value;
        newUser.username = this.username.value;
        newUser.mail = this.mail.value;
        newUser.name = this.name.value;
        newUser.pic = this.imgURL;
        console.log('c');
        this.http.post<User>('https://wws-edge-service.herokuapp.com/users/create', newUser).subscribe(o => console.log(o));
        this.router.navigate(['/login'], { relativeTo: this.route });
      }
    });
  }

  goToLogin(){
    this.router.navigate(['/login'], { relativeTo: this.route });
  }

  get username() {
    return this.myForm.get('username');
  }

  get password() {
    return this.myForm.get('password');
  }

  get bio() {
    return this.myForm.get('bio');
  }

  get name() {
    return this.myForm.get('name');
  }

  get mail() {
    return this.myForm.get('mail');
  }

  public  onFileChanged(event) {
    console.log(event);
    console.log(event.path[1]);
    this.selectedFile = event.target.files[0];

    // Below part is used to display the selected image
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }

  onUpload() {
    const uploadData = new FormData();
    uploadData.append('myFile', this.selectedFile);
    console.log(uploadData);
    let img = new ImageModel();
    img.id = 5;
    img.name = 'holahola';
    img.type = 'png',
    img.pic = this.selectedFile;
    this.receivedImageData = img;
    this.convertedImage = this.imgURL;
    this.isOn = true;
  }

}