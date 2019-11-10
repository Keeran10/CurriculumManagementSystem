// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFormComponent } from './edit-form.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Course } from '../models/course';

describe('EditFormComponent', () => {
  let component: EditFormComponent;
  let fixture: ComponentFixture<EditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterModule.forRoot([]),
        HttpClientTestingModule
      ],
      declarations: [EditFormComponent],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
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

  it('should not format prerequisites if there are none', () => {
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
