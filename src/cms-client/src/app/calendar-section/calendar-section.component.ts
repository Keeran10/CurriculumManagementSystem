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
import { CookieService } from 'ngx-cookie-service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Section } from '../models/section';
import { SectionExtras } from '../models/section-extras';
import { SupportDocumentComponent } from '../support-documents/support-documents.component';
import { Course } from '../models/course';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-calendar-section',
  templateUrl: './calendar-section.component.html',
  styleUrls: ['./calendar-section.component.css']
})
export class CalendarSectionComponent implements OnInit {

  @ViewChild(SupportDocumentComponent, { static: false })
  supportDocumentComponent: SupportDocumentComponent;

  originalCourses = [];
  editedCourses: Course[] = [];
  printedCourses: Course[] = [];
  
  removedPrintedCourses: Number[] = [];
  addedPrintedCourses: Number[] = [];

  courseIds = [];

  isDoneLoading = false;

  id: string;
  sectionOriginal: Section = new Section();
  sectionEditable: Section = new Section();

  extrasModel = new SectionExtras();
  editedExtraModel = new SectionExtras();

  allCourses: Course[] = [];
  myControl = new FormControl();

  selectedFiles: FileList;
  currentFile: File;
  files: File[] = [];

  isDeleteVisible = true;

  constructor(private route: ActivatedRoute, private api: ApiService,
              private cookieService: CookieService,
              private router: Router) { }

  ngOnInit() {

    this.currentFile = null;
    this.selectedFiles = null;
    this.files = null;

    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');

      // quick fix for calendar request through matthew's component on dossier
      if (this.id === '0') {
        this.id = '1';
      }
    });

    const requestId = this.cookieService.get('request');
    const packageId = this.cookieService.get('package');
    const userId = this.cookieService.get('user');
    this.extrasModel.packageId = Number(packageId);
    this.editedExtraModel.packageId = Number(packageId);
    this.extrasModel.userId = Number(userId);
    this.editedExtraModel.userId = Number(userId);
    this.extrasModel.requestId = Number(requestId);
    this.editedExtraModel.requestId = Number(requestId);
    if (this.id === '0') {
      this.sectionEditable = new Section();
      this.sectionOriginal = Object.assign({}, this.sectionEditable);
      this.sectionOriginal.sectionId = null;
      this.sectionOriginal.firstCore = null;
      this.sectionOriginal.secondCore = null;
      this.isDeleteVisible = false;
    } else if (requestId === '0') {
      this.api.getSection(this.id).subscribe(data => {
        this.sectionOriginal = data;
        this.sectionEditable = Object.assign({}, data);
        this.sectionEditable.sectionId = this.sectionOriginal.sectionId;
      });
    } else {
      const originalId = this.cookieService.get('originalSection');
      const editedId = this.cookieService.get('editedSection');
      this.api.getSection(originalId).subscribe(data => {
        this.sectionOriginal = data;
      });
      this.api.getSection(editedId).subscribe(data => {
        this.sectionEditable = data;
        this.sectionEditable.id = Number(originalId);
      });
    }

    const packageNumber = this.cookieService.get('package');
    this.api.getCalendar().subscribe(data => {
      this.originalCourses = data.secondCoreCourses;

      // tslint:disable-next-line:no-shadowed-variable
      this.api.getPackage(packageNumber, '4').subscribe(data => {
        const requests = data.requests;
        let i = 0;
        requests.forEach(request => {
          if (request.targetType === 2) {
            // tslint:disable-next-line:no-shadowed-variable
            this.api.getCourse(String(request.targetId)).subscribe(data => {
              this.editedCourses.push(data);
              if ((requests.length - 1) === i) {
                this.isDoneLoading = true;
                this.getPrintedCourses();
              }
              i++;
            });
          }
        });
        if (!requests.find(r => r.targetType === 2)) {
          this.isDoneLoading = true;
          this.getPrintedCourses();
        }
      });
    });

    this.api.getAllCourses().subscribe(data => {
      this.allCourses = data;
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

  public removeCourseFromCore(course){
    if (confirm('Are you sure you want to delete ' + course.title + ' from this core?')) {
      this.removedPrintedCourses.push(course.id);
      this.printedCourses = this.printedCourses.filter(element => element.id != course.id);
    }
  }

  public addCourseToCore(){
    let addedCourse = this.allCourses.find(course => course.title === this.myControl.value);
    if(this.printedCourses.find(c => c.title===addedCourse.title) === undefined){
      this.addedPrintedCourses.push(addedCourse.id);
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

  public submitForm() {
    // bug hotfix for original_id = 0
    this.sectionEditable.id = 1;
    this.editedExtraModel.core_additions = this.addedPrintedCourses;
    this.editedExtraModel.core_removals = this.removedPrintedCourses;

    this.api.submitCalendarSectionForm(this.supportDocumentComponent.documents,
      this.supportDocumentComponent.descriptions, this.sectionEditable, this.editedExtraModel)
      .subscribe(() => this.router.navigate(['/package']));
  }
}
