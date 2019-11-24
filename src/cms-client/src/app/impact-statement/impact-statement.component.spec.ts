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

import { ImpactStatementComponent } from './impact-statement.component';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Observable } from 'rxjs';

describe('ImpactStatementComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatDialogModule, 
        NoopAnimationsModule,
        HttpClientTestingModule
      ],
      declarations: [ ImpactStatementComponent ],
      providers: [
        ApiService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();;
  }));

  describe('Impact statement tests', ()=> {
    function setup() {
      const fixture = TestBed.createComponent(ImpactStatementComponent);
      const component = fixture.componentInstance;
      const apiService = TestBed.get(ApiService);
      const httpClient = TestBed.get(HttpTestingController);
      const dialog = TestBed.get(MatDialog);

      return { fixture, component, apiService, httpClient, dialog };
    }

    it('should create', () => {
      const { component } = setup();
      expect(component).toBeTruthy();
    });

    it('should set impact on showImpact', () => {
      const { component, apiService } = setup();
      spyOn(apiService, 'getImpact').and.returnValue(new Observable((observer) => {
    
        // observable execution
        observer.next('test');
        observer.complete();
      }));
      spyOn(component, 'openDialog');
      component.showImpact();
      expect(component.impact).toEqual('test');
      expect(component.openDialog).toHaveBeenCalled();
    });
  });
});
