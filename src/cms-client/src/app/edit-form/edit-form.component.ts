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
import { Component, ViewChild, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { CourseExtras } from '../models/course-extras';
import { SupportDocumentComponent } from '../support-documents/support-documents.component';
import { distinct } from 'rxjs/operators';
import { DegreeRequirement } from '../models/degree-requirement';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})

export class EditFormComponent implements OnInit {

  @ViewChild(SupportDocumentComponent, { static: false })
  supportDocumentComponent: SupportDocumentComponent;

  id: string;
  courseOriginal: Course = new Course();
  courseEditable: Course = new Course();

  model = new CourseExtras();
  editedModel = new CourseExtras();

  selectedFiles: FileList;
  currentFile: File;
  files: File[] = [];

  isDeleteVisible = true;

  availableDegrees = [];
  availableCores = [];

  constructor(private route: ActivatedRoute, private api: ApiService,
              private cookieService: CookieService,
              private router: Router) {
  }

  ngOnInit() {

    this.currentFile = null;
    this.selectedFiles = null;
    this.files = null;

    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const requestId = this.cookieService.get('request');
    const packageId = this.cookieService.get('package');
    const userId = this.cookieService.get('user');
    this.model.packageId = Number(packageId);
    this.editedModel.packageId = Number(packageId);
    this.model.userId = Number(userId);
    this.editedModel.userId = Number(userId);
    this.model.requestId = Number(requestId);
    this.editedModel.requestId = Number(requestId);
    if (this.id === '0') {
      this.courseEditable = new Course();
      this.courseOriginal = Object.assign({}, this.courseEditable);
      this.courseOriginal.number = null;
      this.courseOriginal.credits = null;
      this.isDeleteVisible = false;
    } else if (requestId === '0') {
      this.api.getCourse(this.id).subscribe(data => {
        this.courseOriginal = data;
        this.courseEditable = Object.assign({}, data);
        this.courseEditable.degreeRequirements = this.courseOriginal.degreeRequirements;
        this.setRequisitesStrings(data, this.model);
        this.setRequisitesStrings(data, this.editedModel);
      });
    } else {
      const originalId = this.cookieService.get('originalCourse');
      const editedId = this.cookieService.get('editedCourse');
      this.api.getCourse(originalId).subscribe(data => {
        this.courseOriginal = data;
        console.log(this.courseOriginal);
        this.setRequisitesStrings(data, this.model);
      });
      this.api.getCourse(editedId).subscribe(data => {
        this.courseEditable = data;
        console.log(this.courseEditable);
        this.courseEditable.id = Number(originalId);
        this.setRequisitesStrings(data, this.editedModel);
      });
    }

    this.api.getDegreeRequirements('4').subscribe(data => {
      this.availableDegrees = data;
      console.log(this.availableDegrees);
      this.getCoresOfDegree(this.availableDegrees[0]);
    });
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

  public submitForm() {
    this.api.submitCourseRequestForm(this.supportDocumentComponent.documents,
      this.supportDocumentComponent.descriptions, this.courseEditable, this.editedModel)
      .subscribe(() => this.router.navigate(['/package']));
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    console.log(this.selectedFiles);
    this.files.push(this.selectedFiles.item(0));
    console.log(this.files);
  }

  public openDeleteDialog() {
    if (confirm('Are you sure you want to delete this course?')) {

      this.api.submitDeleteCourseRequestForm(this.supportDocumentComponent.documents,
        this.supportDocumentComponent.descriptions, this.courseEditable, this.editedModel)
        .subscribe(() => this.router.navigate(['/package']));
    }
  }

  public getCoresOfDegree(degreeName) {
    const degree = this.availableDegrees.find(e => e.name === degreeName);
    let cores = [];
    if (degree != null) {
      degree.degreeRequirements.forEach(d => {
        cores.push(d.core);
      });
    }
    cores.push('None');
    cores = [...new Set(cores)];
    return cores;
  }

  public changeDegree(event, i) {
    if (event.isUserInput) {
      console.log(event.source.value);
      const degree = this.availableDegrees.find(e => e.name === event.source.value);
      this.courseEditable.degreeRequirements[i].degree.credits = degree.credits;
      this.courseEditable.degreeRequirements[i].degree.id = degree.id;
      this.courseEditable.degreeRequirements[i].degree.level = degree.level;
    }
  }

  public addDegree() {
    const degreeReq = new DegreeRequirement();
    degreeReq.core = 'None';
    degreeReq.id = 0;
    degreeReq.degree = Object.assign({}, this.courseEditable.degreeRequirements[0].degree);
    this.courseEditable.degreeRequirements.push(degreeReq);
  }

  public removeDegree() {
    this.courseEditable.degreeRequirements.pop();
  }
}
