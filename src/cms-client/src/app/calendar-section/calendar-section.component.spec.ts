/* // MIT License

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
// SOFTWARE. */

import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CookieService } from 'ngx-cookie-service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { CalendarSectionComponent } from './calendar-section.component';
import { MatAutocompleteModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

describe('CalendarSectionComponent', () => {
  let component: CalendarSectionComponent;
  let fixture: ComponentFixture<CalendarSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        MatAutocompleteModule, 
        MatFormFieldModule,
        MatInputModule,
        NoopAnimationsModule
      ],
      declarations: [ CalendarSectionComponent ],
      providers: [
        ApiService,
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
