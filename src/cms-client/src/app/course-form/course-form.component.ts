import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../service/course.service';
import { Course } from '../model/course';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent implements OnInit {

  course: Course;

  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService) {
    this.course = new Course();
  }

  onSubmit() {
    this.courseService.save(this.course).subscribe(result => this.gotoCourseList());
  }

  gotoCourseList() {
    this.router.navigate(['/courses']);
  }

  ngOnInit() {
  }

}
