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
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { LoginComponent } from './login.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ApiService } from '../backend-api.service';
import { CookieService } from 'ngx-cookie-service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';

describe('LoginComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule
      ],
      declarations: [ LoginComponent ],
      providers: [
        ApiService,
        CookieService,
        FormBuilder
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Login tests', ()=> {
    function setup() {
      const fixture = TestBed.createComponent(LoginComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const cookieService = TestBed.get(CookieService); 
      const httpClient = TestBed.get(HttpTestingController);
      const router = TestBed.get(Router);
      const activatedRoute = TestBed.get(ActivatedRoute);

      return { fixture, component, apiService, cookieService, httpClient, router, activatedRoute };
    }

    it('should create', () => {
      const { component } = setup();
      expect(component).toBeTruthy();
    });

    it('should set cookies when a user submits their login information', () => {
      const { component, apiService, cookieService } = setup();
      const user = {
        id: 10,
        firstName: 'firstTest',
        lastName: 'lastTest',
        userType: 'testUser',
        email: 'test@email.com',
        password: '123456'
      }
      spyOn(apiService, 'setCredentials').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next(user);
        observer.complete();
      }));
      spyOn(cookieService, 'set');
      component.OnSubmit('fakename', 'fakepass');
      expect(cookieService.set).toHaveBeenCalledTimes(2);
    });

  });
});
