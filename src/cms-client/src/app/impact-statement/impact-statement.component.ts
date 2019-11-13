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

import { Component, Inject, OnInit, Input } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../backend-api.service';
import { Course } from '../models/course';
import { CourseExtras } from '../models/course-extras';
import { first } from 'rxjs/operators';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-impact-statement',
  templateUrl: './impact-statement.component.html',
  styleUrls: ['./impact-statement.component.css']
})
export class ImpactStatementComponent implements OnInit {

  animal: string;
  name: string;

  id: string;
  // tslint:disable-next-line:prefer-const
  impact;
  title: 'test';
  keys;

  @Input() course: Course;
  @Input() courseExtras: CourseExtras;

  constructor(public dialog: MatDialog, private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  showImpact(): void {
    this.apiService.getImpact(this.course, this.courseExtras).subscribe(data => {
      console.log(data);
      this.impact = data;
      this.openDialog();
    });
  }

  openDialog(): void {

    const dialogRef = this.dialog.open(DialogImpactStatementComponent, {
      width: '900px',
      data: {
        OriginalCourseName: this.impact.OriginalCourse.name,
        OriginalCourseNumber: this.impact.OriginalCourse.number,
        OriginalCourseProgram: this.impact.OriginalCourse.program.name,
        OriginalCourseDepartment: this.impact.OriginalCourse.program.department.name,

        DegreeCourseRequiredImpactOriginal: this.impact.DegreeCourseRequiredImpact.original[0],
        DegreeCourseRequiredImpactOriginalKeys: Object.keys(this.impact.DegreeCourseRequiredImpact.original[0] || {}),
        DegreeCourseRequiredImpactRemoved: this.impact.DegreeCourseRequiredImpact.removed[0],
        DegreeCourseRequiredImpactAdded: this.impact.DegreeCourseRequiredImpact.added[0],
        DegreeCourseRequiredImpactUpdated: this.impact.DegreeCourseRequiredImpact.updated[0],
        DegreeCourseRequiredImpactUpdatedKeys: Object.keys(this.impact.DegreeCourseRequiredImpact.updated[0] || {}),

        ProgramImpactOriginal: this.impact.ProgramImpact.original[0],
        ProgramImpactOriginalKeys: Object.keys(this.impact.ProgramImpact.original[0] || {}),
        ProgramImpactRemoved: this.impact.ProgramImpact.removed[0],
        ProgramImpactAdded: this.impact.ProgramImpact.added[0],
        ProgramImpactUpdated: this.impact.ProgramImpact.updated[0],
        ProgramImpactUpdatedKeys: Object.keys(this.impact.ProgramImpact.updated[0] || {}),

        CourseEditsName: this.impact.CourseEdits.name,
        CourseEditsNumber: this.impact.CourseEdits.number,
        CourseEditsCredits: this.impact.CourseEdits.credits,
        CourseEditsTitle: this.impact.CourseEdits.title,
        CourseEditsDescription: this.impact.CourseEdits.description,
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }


}

@Component({
  selector: 'app-dialog-impact-statement',
  templateUrl: 'dialog-impact-statement.html',
})
export class DialogImpactStatementComponent {

  constructor(
    public dialogRef: MatDialogRef<DialogImpactStatementComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }
}
