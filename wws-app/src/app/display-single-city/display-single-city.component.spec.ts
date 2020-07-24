import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplaySingleCityComponent } from './display-single-city.component';

describe('DisplaySingleCityComponent', () => {
  let component: DisplaySingleCityComponent;
  let fixture: ComponentFixture<DisplaySingleCityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplaySingleCityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplaySingleCityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
