import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { CourseService } from '../service/course.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  courses: Course[];

  constructor(private courseService: CourseService) { }

  ngOnInit() {
    this.courseService.findAll().subscribe(data => {this.courses = data;
    });
  }
}
