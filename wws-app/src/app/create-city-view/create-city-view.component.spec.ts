import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCityViewComponent } from './create-city-view.component';

describe('CreateCityViewComponent', () => {
  let component: CreateCityViewComponent;
  let fixture: ComponentFixture<CreateCityViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateCityViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCityViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
