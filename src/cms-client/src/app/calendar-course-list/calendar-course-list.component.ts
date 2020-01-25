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
import { CookieService } from 'ngx-cookie-service';
import { ApiService } from '../backend-api.service';
import { Course } from '../models/course';

@Component({
  selector: 'app-calendar-course-list',
  templateUrl: './calendar-course-list.component.html',
  styleUrls: ['./calendar-course-list.component.css']
})
export class CalendarCourseListComponent implements OnInit {

  originalCourses;
  editedCourses : Course[] = [];
  isGetCalendarCourses = false;
  isGetPackageCourses = false;

  constructor(private cookieService: CookieService,
    private api: ApiService) { }

  ngOnInit() {
    let packageNumber = this.cookieService.get('package');
    this.api.getCalendar().subscribe(data => {
      this.originalCourses = data.secondCoreCourses;
      this.isGetCalendarCourses = true;
    });
    this.api.getPackage(packageNumber, '4').subscribe(data => {
      let requests = data.requests;
      requests.forEach(request => {
        this.api.getCourse(String(request.targetId)).subscribe(data => {
          this.editedCourses.push(data);
        });
      });
      this.isGetPackageCourses = true;
    })
  }

  public logData(){
    console.log(this.originalCourses);
    console.log(this.editedCourses);
  }

}
