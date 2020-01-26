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
import { Router } from '@angular/router';

@Component({
  selector: 'app-calendar-course-list',
  templateUrl: './calendar-course-list.component.html',
  styleUrls: ['./calendar-course-list.component.css']
})
export class CalendarCourseListComponent implements OnInit {

  originalCourses = [];
  editedCourses: Course[] = [];
  printedCourses: Course[] = [];

  courseIds = [];

  isDoneLoading = false;

  constructor(private cookieService: CookieService,
              private api: ApiService,
              private router: Router) { }

  ngOnInit() {
    const packageNumber = this.cookieService.get('package');
    this.api.getCalendar().subscribe(data => {
      this.originalCourses = data.secondCoreCourses;

      this.api.getPackage(packageNumber, '4').subscribe(data => {
        const requests = data.requests;
        let i = 0;
        requests.forEach(request => {
          this.api.getCourse(String(request.targetId)).subscribe(data => {
            this.editedCourses.push(data);
            if ((requests.length - 1) === i) {
              this.isDoneLoading = true;
              this.getPrintedCourses();
            }
            i++;
          });
        });
      });
    });
  }

  public getPrintedCourses() {
    this.originalCourses.forEach(oc => {
      let isRepeated = false;
      this.editedCourses.forEach(ec => {
        if (ec.number === oc.number) {
          isRepeated = true;
          this.printedCourses.push(ec);

          this.courseIds.push(oc.id);
          this.courseIds.push(ec.id);
        }
      });
      if (!isRepeated) {
        this.printedCourses.push(oc);

        this.courseIds.push(oc.id);
        this.courseIds.push(oc.id);
      }
    });
  }

  public navigateToEditFormEdited(courseOriginalId, courseEditedId) {
    this.cookieService.set('originalCourse', courseOriginalId.toString());
    this.cookieService.set('editedCourse', courseEditedId.toString());

    this.router.navigate(['editForm/' + courseOriginalId]);
  }

  public navigateToEditForm(a) {

  }

}
