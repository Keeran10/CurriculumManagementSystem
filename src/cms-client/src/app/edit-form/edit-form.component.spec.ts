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
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Course } from '../models/course';
import { CourseExtras } from '../models/course-extras';
import { CookieService } from 'ngx-cookie-service';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable } from 'rxjs';
import { SupportDocumentComponent } from '../support-documents/support-documents.component';

describe('EditFormComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ],
      declarations: [ EditFormComponent ],
      providers: [
        ApiService,
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Approval pipeline tests', ()=> {
    function setup() {
      const fixture = TestBed.createComponent(EditFormComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const cookieService = TestBed.get(CookieService); 
      const httpClient = TestBed.get(HttpTestingController);
      component.supportDocumentComponent = new SupportDocumentComponent();  

      return { fixture, component, apiService, cookieService, httpClient };
    }

    it('should create', () => {
      const { component } = setup();
      expect(component).toBeTruthy();
    });

    it('should not format prerequisites if there are none', () => {
      const { component } = setup();
      let testCourse: Course = new Course();
      let testCourseExtras = new CourseExtras();
      testCourse.requisites = [];
  
      component.setRequisitesStrings(testCourse, testCourseExtras);
      expect(component.model.prerequisites).toBe("")
    });

    it('should format requisites if they exist', () => {
      const { component } = setup();
      let testCourse: Course = new Course();
      let testCourseExtras = new CourseExtras();
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
          type: "corequisite",
          number: 321,
          isActive: true
        },
        {
          id: 0,
          name: "TEST",
          type: "equivalent",
          number: 456,
          isActive: true
        },
        {
          id: 0,
          name: "TEST",
          type: "equivalent",
          number: 457,
          isActive: true
        },

      ];
  
      component.setRequisitesStrings(testCourse, testCourseExtras);
      expect(testCourseExtras.prerequisites).toBe("SOEN123; ");
      expect(testCourseExtras.corequisites).toBe('MECH321; ');
      expect(testCourseExtras.equivalents).toBe('TEST456 or TEST457; ');
    });

    it('should add documents and send form in submit', () => {
      const { component, apiService } = setup();
      component.supportDocumentComponent.documents = [new File([], "File1"), new File([], "File2")];
      spyOn(apiService, 'submitEditedCourse').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next('test');
        observer.complete();
      }));

      component.submitForm();

      expect(component.editedModel.files).toEqual(component.supportDocumentComponent.documents);
      expect(apiService.submitEditedCourse).toHaveBeenCalled();
    });
    
    it('should highlight changes of ins and del elements', () => {
      const { component } = setup();

      component.courseOriginal.title = 'testname';
      component.courseEditable.title = 'test';
      component.courseEditable.description = 'test description';

      component.highlightChanges();

      const insElements = document.querySelectorAll('ins');
      const delElements = document.querySelectorAll('del');
      insElements.forEach((e) => {
        expect(e.style.background).toEqual('#bbffbb');
      });
  
      delElements.forEach((e) => {
        expect(e.style.background).toEqual('#ffbbbb');
      });
    });

  });
});
