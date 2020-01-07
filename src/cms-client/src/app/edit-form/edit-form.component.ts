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

import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { Course } from '../models/course';
import { Component, ViewChild } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { CourseExtras } from '../models/course-extras';
import { SupportDocumentComponent } from '../support-documents/support-documents.component';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})

export class EditFormComponent {

  @ViewChild(SupportDocumentComponent, { static: false })
  supportDocumentComponent: SupportDocumentComponent;

  id: string;
  courseOriginal: Course = new Course();
  courseEditable: Course = new Course();

  model = new CourseExtras();
  editedModel = new CourseExtras();

  constructor(private route: ActivatedRoute, private api: ApiService,
    private cookieService: CookieService,
    private router: Router) {
  }

  // tslint:disable-next-line:use-lifecycle-interface
  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    let requestId = this.cookieService.get('request');
    let packageId = this.cookieService.get('package');
    let userId = this.cookieService.get('user');
    this.model.packageId = Number(packageId);
    this.editedModel.packageId = Number(packageId);
    this.model.userId = Number(userId);
    this.editedModel.userId = Number(userId);
    this.model.requestId = Number(requestId);
    this.editedModel.requestId = Number(requestId);
    if(this.id === '0'){
      this.courseEditable = {
        id: 0,
        credits: 4,
        degreeRequirements: [],
        description: '',
        equivalent: [],
        isActive: false,
        labHours: 0,
        lectureHours: 0,
        level: 1,
        name: '',
        note: '',
        number: 100,
        outline: '',
        program: null,
        requisites: [],
        title: '',
        tutorialHours: 0
      };
      this.courseOriginal = Object.assign({}, this.courseEditable);
      this.courseOriginal.number = null;
      this.courseOriginal.credits = null;
    }
    else if(requestId === '0'){
      this.api.getCourse(this.id).subscribe(data => {
        this.courseOriginal = data;
        this.courseEditable = Object.assign({}, data);
        this.setRequisitesStrings(data, this.model);
        this.setRequisitesStrings(data, this.editedModel)
      });
    }
    else {
      let originalId = this.cookieService.get('originalCourse');
      let editedId = this.cookieService.get('editedCourse');
      this.api.getCourse(originalId).subscribe(data => {
        this.courseOriginal = data;
        this.setRequisitesStrings(data, this.model);
      });
      this.api.getCourse(editedId).subscribe(data => {
        this.courseEditable = data;
        this.courseEditable.id = Number(originalId);
        this.setRequisitesStrings(data, this.editedModel);
      });
    }
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

  public setRequisitesStrings(course: Course, courseExtras: CourseExtras) {
    let isNextEquivalent = false;
    if (course.requisites.length > 0) {
      course.requisites.forEach(r => {
        switch (r.type) {
          case 'equivalent':
            if (!isNextEquivalent) {
              courseExtras.equivalents += r.name + r.number + ' or ';
            } else {
              courseExtras.equivalents += r.name + r.number + '; ';
            }
            isNextEquivalent = !isNextEquivalent;
            break;
          case 'prerequisite':
            courseExtras.prerequisites += r.name + r.number + '; ';
            break;
          case 'corequisite':
            courseExtras.corequisites += r.name + r.number + '; ';
            break;
        }
      });
    }
  }

  // There have been some backend changes concerning these fields. Will uncomment them and complete implementation later.
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

  public submitForm() {
    this.editedModel.files = this.supportDocumentComponent.documents;
    this.api.submitEditedCourse(this.courseEditable, this.editedModel)
      .subscribe(() => this.router.navigate(['package']))
  }
}
