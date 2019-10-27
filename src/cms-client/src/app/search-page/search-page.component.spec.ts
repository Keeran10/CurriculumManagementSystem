import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatAutocompleteModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { SearchPageComponent } from './search-page.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('SearchPageComponent', () => {
  let component: SearchPageComponent;
  let fixture: ComponentFixture<SearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatAutocompleteModule, 
        MatFormFieldModule,
        MatInputModule
      ],
      declarations: [ SearchPageComponent ],
      schemas: [ NO_ERRORS_SCHEMA ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });


});
