import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Router, ActivatedRoute } from '@angular/router';
import { ImageModel } from '../Model/ImageModel';
import { City } from '../Model/city.model';


@Component({
  selector: 'app-create-city-view',
  templateUrl: './create-city-view.component.html',
  styleUrls: ['./create-city-view.component.css']
})
export class CreateCityViewComponent implements OnInit {
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
      city: ['', [Validators.required]],
      country: ['', [Validators.required]],
      description: ['', [Validators.required, Validators.minLength(50), Validators.maxLength(350)]]
    });
    this.myForm.valueChanges.subscribe(() => console.log);
  }

  submit() {
    this.submitForm();
  }

  submitForm = () => {
    let cityDto = new City();
    cityDto.city = this.city.value;
    cityDto.country = this.country.value;
    cityDto.description = this.description.value;
    cityDto.pic = this.imgURL;
    this.http.post<City>('https://wws-edge-service.herokuapp.com/cities', cityDto, this.httpOptions).subscribe( () => {
      this.router.navigate(['/home'], { relativeTo: this.route });
    });
  }

  get city() {
    return this.myForm.get('city');
  }

  get country() {
    return this.myForm.get('country');
  }

  get description() {
    return this.myForm.get('description');
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