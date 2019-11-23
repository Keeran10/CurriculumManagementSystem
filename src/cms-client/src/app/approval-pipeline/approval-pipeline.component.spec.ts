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
import { ApprovalPipelineComponent } from './approval-pipeline.component';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CookieService } from 'ngx-cookie-service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs';

describe('ApprovalPipelineComponent', () => {

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ],
      declarations: [ ApprovalPipelineComponent ],
      providers: [
        ApiService,
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Approval tests', ()=> {
    function setup() {
      const fixture = TestBed.createComponent(ApprovalPipelineComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const cookieService = TestBed.get(CookieService); 
      const httpClient = TestBed.get(HttpTestingController);

      return { fixture, component, apiService, cookieService, httpClient };
    }

    it('should create component', () => {
      const { component } = setup();
      expect(component).toBeTruthy();
    });

    it('should get the ID from cookie on init', () => {
      const { component, cookieService} = setup();
      spyOn(cookieService, 'get').and.returnValue('2');

      component.ngOnInit();
      expect(component.packageId).toBe(2);
    });

    it('should display an alert if no pipeline has been selected', () => {
      const { component } = setup();
      spyOn(window, "alert");
      component.custom([]);
      expect(window.alert).toHaveBeenCalledWith('Cannot submit blank pipeline');
    })

    it('should save the list of custom pipeline steps to "customPipeline"', () => {
      const { component } = setup();
      const listOfStrings = [
        {value:'A'},
        {value:'list'},
        {value:'of'},
        {value:'strings'}
      ];
      const listOfStringsValues = [
        'A',
        'list',
        'of',
        'strings'
      ]
      component.custom(listOfStrings);
      expect(component.customPipeline).toEqual(listOfStringsValues);
    })

    it('should save the pipeline', () => {
      const { component, apiService } = setup();
      spyOn(apiService, 'savePipeline').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next('test');
        observer.complete();
      }));
      component.predefined();
      expect(apiService.savePipeline).toHaveBeenCalled();
    });

  });
});