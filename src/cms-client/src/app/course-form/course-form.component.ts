import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { Course } from '../model/course';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent implements OnInit {

  course: Course;

  constructor(private route: ActivatedRoute, private router: Router, private apiService: ApiService) {
    this.course = new Course();
  }

  onSubmit() {
    this.apiService.saveCourse(this.course).subscribe(result => this.gotoCourseList());
  }

  gotoCourseList() {
    this.router.navigate(['/courses']);
  }

  ngOnInit() {
  }

}
