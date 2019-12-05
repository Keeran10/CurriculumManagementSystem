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

import { async, TestBed } from '@angular/core/testing';

import { CourseFormComponent } from './course-form.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

describe('CourseFormComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule
      ],
      declarations: [ CourseFormComponent ],
      providers: [
        ApiService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Course form tests', ()=> {
    function setup() {
      const fixture = TestBed.createComponent(CourseFormComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const httpClient = TestBed.get(HttpTestingController);
      const router = TestBed.get(Router);

      return { fixture, component, apiService, httpClient, router };
    }

    it('should create component', () => {
      const { component } = setup();
      expect(component).toBeTruthy();
    });

    it('should navigate to next page on submit after saving', () => {
      const { component, apiService, router } = setup();
      spyOn(apiService, 'saveCourse').and.returnValue(new Observable((observer) => {
        // observable execution
        observer.next('test');
        observer.complete();
      }));
      spyOn(router, 'navigate');
      component.onSubmit();

      expect(apiService.saveCourse).toHaveBeenCalled();
      expect(router.navigate).toHaveBeenCalled();
    });

  });
});
