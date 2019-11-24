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
import { PipelineTrackingComponent } from './pipeline-tracking.component';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CookieService } from 'ngx-cookie-service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { Observable } from 'rxjs';

describe('PipelineTrackingComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [ PipelineTrackingComponent ],
      providers: [
        ApiService,
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Pipeline Tracking tests', ()=> {
    function setup() {
      const fixture = TestBed.createComponent(PipelineTrackingComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const cookieService = TestBed.get(CookieService); 
      const httpClient = TestBed.get(HttpTestingController);

      return { fixture, component, apiService, cookieService, httpClient };
    }

    it('should create', () => {
      const { component } = setup();
      expect(component).toBeTruthy();
    });

    it('should get the package ID', () => {
      const { component, cookieService } = setup();
      spyOn(cookieService, 'get').and.returnValue('15');

      component.getPackageID();
      expect(component.id).toEqual(15);
    });

    it('should get the pipeline', () => {
      const { component, apiService } = setup();
      const pipelines = ['pipeline1', 'pipeline2'];
      spyOn(apiService, 'getApprovalPipeline').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next(pipelines);
        observer.complete();
      }));

      component.getPipeline();
      expect(component.pipeline).toEqual(pipelines);
    });

    it('should get the package location', () => {
      const { component, apiService } = setup();
      spyOn(apiService, 'getCurrentPosition').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next('test');
        observer.complete();
      }));;

      component.getPackageLocation();
      expect(apiService.getCurrentPosition).toHaveBeenCalled();
    });

    it('should generate pdf', () => {
      const { component, apiService } = setup();
      spyOn(apiService, 'generatePdf').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next('test');
        observer.complete();
      }));;

      component.generatePDF();
      expect(apiService.generatePdf).toHaveBeenCalled();
    });
  });
});
