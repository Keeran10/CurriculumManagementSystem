import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFormComponent } from './edit-form.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Course } from '../model/course';

describe('EditFormComponent', () => {
  let component: EditFormComponent;
  let fixture: ComponentFixture<EditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterModule.forRoot([]),
        HttpClientTestingModule
      ],
      declarations: [ EditFormComponent ],
      schemas: [ NO_ERRORS_SCHEMA ],
      providers:[
        ApiService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should not format prerequisites if there are none', () =>{
    let testCourse: Course = new Course();
    testCourse.requisites = [];
    
    component.setRequisitesStrings(testCourse);
    expect(component.model.prerequisites).toBe("")
  });

  it('should format prerequisites if they exist', () => {
    let testCourse: Course = new Course();
    testCourse.requisites = [
        {
          id: 0,
          name: "SOEN",
          type: "prerequisite",
          number: 123,
          isActive: true
        },
        {
          id: 0,
          name: "MECH",
          type: "prerequisite",
          number: 321,
          isActive: true
        },
        {
          id: 0,
          name: "TEST",
          type: "prerequisite",
          number: 456,
          isActive: true
        }
      ];
    
    component.setRequisitesStrings(testCourse);
    expect(component.model.prerequisites).toBe("SOEN123; MECH321; TEST456; ")
  });
});
