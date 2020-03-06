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
import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Course } from '../models/course';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-calendar-course-list',
  templateUrl: './calendar-course-list.component.html',
  styleUrls: ['./calendar-course-list.component.css']
})
export class CalendarCourseListComponent implements OnInit, OnChanges {

  @Input('section-editable-core') core: Course[];
  @Input('core-name') coreName: string;
  @Input('printed-list') printedCourses: Course[];
  @Input('removed-list') removed: Number[];
  @Input('added-list') added: Number[];
  @Input('all-courses') allCourses: Course[];

  hasCoreLoaded = false;

  myControl = new FormControl();

  constructor(private cookieService: CookieService, private router: Router) { }

  ngOnInit() {
  }

  ngOnChanges(){
    if(!this.hasCoreLoaded){
      if(this.core.length > 0){
        this.printedCourses = this.core;
        this.hasCoreLoaded = true;
      }
    }
  }

  public removeCourseFromCore(course){
    if (confirm('Are you sure you want to delete ' + course.title + ' from this core?')) {
      this.removed.push(course.id);
      this.printedCourses = this.printedCourses.filter(element => element.id != course.id);
    }
  }

  public addCourseToCore(){
    let addedCourse = this.allCourses.find(course => course.title === this.myControl.value);
    if(this.printedCourses.find(c => c.title===addedCourse.title) === undefined){
      this.added.push(addedCourse.id);
      this.printedCourses.push(addedCourse);
      this.printedCourses = this.printedCourses.sort(function(a,b){
        if(a.number<b.number){
          return -1;
        }
        if(a.number>b.number){
          return 1;
        }
        return 0;
      })
    }
  }

  public navigateToEditFormEdited(id) {
    this.cookieService.set('originalCourse', id.toString());
    this.cookieService.set('editedCourse', id.toString());

    this.router.navigate(['editForm/' + id]);
  }


}
