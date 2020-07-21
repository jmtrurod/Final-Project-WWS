import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { Post } from '../Model/post.model';
import { PostCreateDto } from '../dto/postCreate.dto';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-create-post-form',
  templateUrl: './create-post-form.component.html',
  styleUrls: ['./create-post-form.component.css']
})
export class CreatePostFormComponent implements OnInit {
  @Input()
  username: string;

  @Output()
  postEmiter: EventEmitter<Post> = new EventEmitter<Post>();

  isForm = false;
  myForm: FormGroup;
  cities: string[];
  postCreateDto: PostCreateDto = new PostCreateDto();

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.cookieService.get('auth'),
    }),
  };

  constructor(private http: HttpClient, private fb: FormBuilder, private cookieService: CookieService) { }

  ngOnInit(): void {
    this.myForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(25), Validators.minLength(4)]],
      url: '',
      content: ['', [Validators.required, Validators.maxLength(300), Validators.minLength(20)]],
      city: ['', [Validators.required]],
      theme: ['', [Validators.required]]
     });
    this.myForm.valueChanges.subscribe(() => console.log );

    this.http.get<string[]>('http://localhost:8080/cities', this.httpOptions).subscribe(
      cities => this.cities = cities
    );
  }

  isFormToTrue(){
    this.isForm = true;
  }

  isFormToFalse(){
    this.isForm = false;
    this.myForm.reset();
    Object.keys(this.myForm.controls).forEach(key => {
      this.myForm.get(key).setErrors(null) ;
    });
  }

  submitForm(){
    let cityId: string;
    cityId = this.city.value;
    this.postCreateDto.city = cityId.split('-')[0];
    this.postCreateDto.country = cityId.split('-')[1];
    this.postCreateDto.content = this.content.value;
    this.postCreateDto.theme = this.theme.value;
    this.postCreateDto.title = this.title.value;
    this.postCreateDto.url = this.url.value;
    this.postCreateDto.username = this.username;

    this.http.post<Post>('http://localhost:8080/posts/create?username=' + this.username, this.postCreateDto, this.httpOptions)
    .subscribe(post => this.postEmiter.emit(post));
    this.isForm = false;
    this.myForm.reset();
    Object.keys(this.myForm.controls).forEach(key => {
      this.myForm.get(key).setErrors(null) ;
    });
  }

  get title(){
    return this.myForm.get('title');
  }
  get url(){
    return this.myForm.get('url');
  }
  get city(){
    return this.myForm.get('city');
  }
  get content(){
    return this.myForm.get('content');
  }
  get theme(){
    return this.myForm.get('theme');
  }
}
