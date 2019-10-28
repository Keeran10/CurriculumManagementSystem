import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { ApiService } from '../backend-api.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  courses: Course[];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getAllCourses().subscribe(data => {this.courses = data;
    });
  }
}
