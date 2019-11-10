import { Component, Inject, OnInit, Input } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from '../backend-api.service';
import { Course } from '../models/course';
import { CourseExtras } from '../model/course-extras';
import {first} from 'rxjs/operators';

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
    // this.apiService.getImpact().subscribe(data => this.impact = data);
  }

  showImpact(): void {
    this.apiService.getImpact(this.course, this.courseExtras).subscribe(data => this.impact = data);
    this.openDialog();
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


        DegreeCourseElectiveImpactRemoved: this.impact.DegreeCourseElectiveImpact.removed[0],
        DegreeCourseElectiveImpactAdded: this.impact.DegreeCourseElectiveImpact.added[0],

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
