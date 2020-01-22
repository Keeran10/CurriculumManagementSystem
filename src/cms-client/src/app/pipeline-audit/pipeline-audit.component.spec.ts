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
import { PipelineAuditComponent } from './pipeline-audit.component';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CookieService } from 'ngx-cookie-service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('RevisionsComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule
      ],
      declarations: [PipelineAuditComponent],
      providers: [
        ApiService,
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();
  }));

  describe('Pipeline Revisions tests', () => {
    function setup() {
      const fixture = TestBed.createComponent(PipelineAuditComponent);
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

  });
});
