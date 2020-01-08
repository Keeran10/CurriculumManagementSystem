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
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
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
      declarations: [EditFormComponent],
      providers: [
        ApiService,
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Edit form tests', () => {
    function setup() {
      const fixture = TestBed.createComponent(EditFormComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const cookieService = TestBed.get(CookieService);
      const httpClient = TestBed.get(HttpTestingController);
      const router = TestBed.get(Router);
      const activatedRoute = TestBed.get(ActivatedRoute);
      component.supportDocumentComponent = new SupportDocumentComponent();

      return { fixture, component, apiService, cookieService, httpClient, router, activatedRoute };
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
      spyOn(apiService, 'submitCourseRequestForm').and.returnValue(new Observable((observer) => {

        // observable execution
        observer.next('test');
        observer.complete();
      }));

      component.submitForm();

      expect(component.currentFile).toEqual(component.supportDocumentComponent.documents[0]);
      expect(apiService.submitCourseRequestForm).toHaveBeenCalled();
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

    it('should load the course data into the original and editable models', () => {
      const { component, cookieService, apiService, router, activatedRoute } = setup();
      spyOn(cookieService, 'get').and.returnValue('0');
      spyOn(activatedRoute, 'paramMap').and.returnValue(new Observable((observer) => {

        // observable execution
        observer.next('test');
        observer.complete();
      }));
      const testCourse = {
        id: 0,
        credits: 4,
        degreeRequirements: [],
        description: 'description',
        equivalent: [],
        isActive: true,
        labHours: 3,
        lectureHours: 4,
        level: 1,
        name: 'TEST',
        note: '',
        number: 123,
        outline: '',
        program: null,
        requisites: [],
        title: 'Test title',
        tutorialHours: 4
      }
      spyOn(apiService, 'getCourse').and.returnValue(new Observable((observer) => {

        // observable execution
        observer.next(testCourse);
        observer.complete();
      }));

      component.ngOnInit();

      expect(component.courseOriginal).toEqual(testCourse);
      expect(component.courseEditable).toEqual(testCourse);
    });

    it('should load the original course data into the original and the edited course into the editable model', () => {
      const { component, cookieService, apiService, router, activatedRoute } = setup();
      spyOn(cookieService, 'get').withArgs('originalCourse').and.returnValue('1')
        .withArgs('editedCourse').and.returnValue('2')
        .withArgs('request').and.returnValue('1')
        .withArgs('package').and.returnValue('1')
        .withArgs('user').and.returnValue('1');
      spyOn(activatedRoute, 'paramMap').and.returnValue(new Observable((observer) => {

        // observable execution
        observer.next('test');
        observer.complete();
      }));
      const testCourseOrignal = {
        id: 1,
        credits: 4,
        degreeRequirements: [],
        description: 'description',
        equivalent: [],
        isActive: true,
        labHours: 3,
        lectureHours: 4,
        level: 1,
        name: 'TEST',
        note: '',
        number: 123,
        outline: '',
        program: null,
        requisites: [],
        title: 'Test title',
        tutorialHours: 4
      }
      const testCourseEdited = {
        id: 2,
        credits: 4,
        degreeRequirements: [],
        description: 'Edited description',
        equivalent: [],
        isActive: true,
        labHours: 3,
        lectureHours: 4,
        level: 1,
        name: 'TEST',
        note: '',
        number: 321,
        outline: '',
        program: null,
        requisites: [],
        title: 'Test title Edited',
        tutorialHours: 4
      }
      spyOn(apiService, 'getCourse').withArgs('1').and.returnValue(new Observable((observer) => {

        // observable execution
        observer.next(testCourseOrignal);
        observer.complete();
      })).withArgs('2').and.returnValue(new Observable((observer) => {

        // observable execution
        observer.next(testCourseEdited);
        observer.complete();
      }));

      component.ngOnInit();

      expect(component.courseOriginal).toEqual(testCourseOrignal);
      expect(component.courseEditable).toEqual(testCourseEdited);
    });
  });
});
