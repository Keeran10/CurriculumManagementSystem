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

import { PackageComponent } from './package.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ApiService } from '../backend-api.service';
import { CookieService } from 'ngx-cookie-service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Package } from '../models/package';
import { componentFactoryName } from '@angular/compiler';

describe('PackageComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ],
      declarations: [ PackageComponent ],
      providers: [
        ApiService,
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Package tests', ()=> {
    function setup() {
      const fixture = TestBed.createComponent(PackageComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const cookieService = TestBed.get(CookieService); 
      const httpClient = TestBed.get(HttpTestingController);
      const router = TestBed.get(Router); 

      return { fixture, component, apiService, cookieService, httpClient, router };
    }

    it('should create', () => {
      const { component } = setup();
      expect(component).toBeTruthy();
    });

    it('should get packages and set user name on init', () => {
      const { component, cookieService, apiService } = setup();
      cookieService.set('userName', 'testName');
      let packages = new Array();
      packages.push({
        id: 5,
        approvals: {},
        department: {},
        pdfFile: new File([], 'test'),
        requests: [],
        supportingDocuments: []
      });
      packages.push({
        id: 10,
        approvals: {},
        department: {},
        pdfFile: new File([], 'test'),
        requests: [],
        supportingDocuments: []
      })
      spyOn(apiService, 'getAllPackages').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next(packages);
        observer.complete();
      }));
      component.ngOnInit();

      expect(component.packages).toEqual(packages);
      expect(component.userName).toEqual('testName');
    });

    it('should make an api call to create a new package', () => {
      const {component, apiService} = setup();
      const testPackage = new Package();
      spyOn(apiService, 'getPackage').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next(testPackage);
        observer.complete();
      }));
      component.createNewPackage();
      expect(apiService.getPackage).toHaveBeenCalled();
    });

    it('should make an api call to create a generate a pdf', () => {
      const {component, apiService} = setup();
      spyOn(apiService, 'generatePdf').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next(false);
        observer.complete();
      }));;
      component.generatePdf(1, 1);
      expect(apiService.generatePdf).toHaveBeenCalled();
    });

    it('should select package and navigate to next href', () => {
      const { component, apiService, cookieService, router } = setup();
      spyOn(router, 'navigate');
      component.packageSelect(15, 0, 'testHref');
      expect(cookieService.get('package')).toEqual('15');
      expect(cookieService.get('request')).toEqual('0');
      expect(router.navigate).toHaveBeenCalled();
    });
  });
});
