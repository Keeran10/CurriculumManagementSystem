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

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { Course } from '../model/course';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})

export class EditFormComponent implements OnInit {

  id: string;
  courseOriginal: Course = new Course();
  courseEditable: Course = new Course();

  model = new CourseExtras();
  editedModel = new CourseExtras();

  constructor(private route: ActivatedRoute, private api: ApiService) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    this.api.getCourse(this.id).subscribe(data => {
      console.log(data);
      this.courseOriginal = data;
      this.courseEditable = Object.assign({}, data);
      this.setRequisitesStrings(data);
      this.editedModel = Object.assign({}, this.model);
    });
  }

  ngAfterViewInit() {

  }

  public highlightChanges(): void {
    const insElements = document.querySelectorAll('ins');
    const delElements = document.querySelectorAll('del');

    insElements.forEach((e) => {
      e.style.background = '#bbffbb';
    });

    delElements.forEach((e) => {
      e.style.background = '#ffbbbb';
    });
  }

  public setRequisitesStrings(course: Course) {
    if (course.requisites.length > 0) {
      course.requisites.forEach(r => {
        if (r.type === 'prerequisite') {
          this.model.prerequisites += r.name + r.number + '; ';
        }
        else if (r.type === 'corequisite') {
          this.model.corequisites += r.name + r.number + '; ';
        }
      })
    }
  }

  //There have been some backend changes concerning these fields. Will uncomment them and complete implementation later.
  /*
    public setDegreesStrings(course: Course) {
      if(course.degreeRequirements.length > 0){
        course.degreeRequirements.forEach(d => {
          if(d.type === 'degree'){
            do x
          }
          else if(d.type === 'elective'){
            do y
          }
        })
      }
    */
  public parsePrerequisitesString(prerequisites: string): Object {
    return {};
  }

  public submitForm() {
    this.api.submitEditedCourse(this.courseEditable, this.editedModel)
      .subscribe(data => { console.log(data) })
  }
}

export class CourseExtras {
  prerequisites: string;
  corequisites: string;
  antirequisites: string;
  rationale: string;
  implications: string;

  constructor() {
    this.prerequisites = '';
    this.corequisites = '';
    this.antirequisites = '';
    this.rationale = '';
    this.implications = '';
  }
};