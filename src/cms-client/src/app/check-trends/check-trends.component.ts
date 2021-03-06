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
import { Article } from '../models/article';
import { Course } from '../models/course';
import { CourseExtras } from '../models/course-extras';

export interface DialogData {
  name: string;
}

@Component({
  selector: 'app-check-trends',
  templateUrl: './check-trends.component.html',
  styleUrls: ['./check-trends.component.css']
})
export class CheckTrendsComponent implements OnInit {

  articles: Article[];

  @Input() course: Course;
  @Input() courseExtras: CourseExtras;

  constructor(public dialog: MatDialog, private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  showTrends(): void {
    this.apiService.getTrends(this.course, this.courseExtras).subscribe(data => {
      console.log("ARTICLES: ");
      console.log(data);
      this.articles = data;
      this.openDialog();
    });
  }

  openDialog(): void {
    console.log("this.articles=");
    console.log(this.articles);

    for (let i = 0; i < this.articles.length; i++) {
      var d = new Date(this.articles[i].publishedAt);
      var raw_month = d.getMonth();
      var raw_day = d.getDay();
      var month = "";
      var day = "";

      if (raw_month == 0) { month = "January"; }
      if (raw_month == 1) { month = "Feburary"; }
      if (raw_month == 2) { month = "March"; }
      if (raw_month == 3) { month = "April"; }
      if (raw_month == 4) { month = "May"; }
      if (raw_month == 5) { month = "June"; }
      if (raw_month == 6) { month = "July"; }
      if (raw_month == 7) { month = "August"; }
      if (raw_month == 8) { month = "September"; }
      if (raw_month == 9) { month = "October"; }
      if (raw_month == 10) { month = "November"; }
      if (raw_month == 11) { month = "December"; }

      if (raw_day == 0) { day = "Sunday"; }
      if (raw_day == 1) { day = "Monday"; }
      if (raw_day == 2) { day = "Tuesday"; }
      if (raw_day == 3) { day = "Wednesday"; }
      if (raw_day == 4) { day = "Thursday"; }
      if (raw_day == 5) { day = "Friday"; }
      if (raw_day == 6) { day = "Saturday"; }

      this.articles[i].publishedAt = "published on " + day + ", " + month + " " + d.getDate() + ", " + d.getFullYear() + ".";
    }

    const dialogRef = this.dialog.open(DialogCheckTrendsComponent, {
      width: '50%',
      height: 'auto',
      data: {
        articles: this.articles,
        course: this.course,
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }


}

@Component({
  selector: 'app-dialog-check-trends',
  templateUrl: 'dialog-check-trends.html',
})
export class DialogCheckTrendsComponent {

  constructor(
    public dialogRef: MatDialogRef<DialogCheckTrendsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }
}
