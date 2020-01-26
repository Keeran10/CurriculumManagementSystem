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
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../backend-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-calendar-sections',
  templateUrl: './calendar-sections.component.html',
  styleUrls: ['./calendar-sections.component.css']
})
export class CalendarSectionsComponent implements OnInit {

  constructor(private api: ApiService,
    private router: Router) { }

  ngOnInit() {
  }

  public getCalendarData(){
    this.api.getCalendar().subscribe(data => console.log(data));
    this.router.navigate(['tempPage']);
  }
}
