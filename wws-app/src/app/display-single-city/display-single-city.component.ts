import { Component, OnInit, Input } from '@angular/core';
import { City } from '../Model/city.model';

@Component({
  selector: 'app-display-single-city',
  templateUrl: './display-single-city.component.html',
  styleUrls: ['./display-single-city.component.css']
})
export class DisplaySingleCityComponent implements OnInit {
  @Input()
  city: City;

  constructor() { }

  ngOnInit(): void {
  }

}
