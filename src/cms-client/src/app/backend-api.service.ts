import { HttpClient, HttpParams } from '@angular/common/http';
<<<<<<< HEAD
import { Course } from './models/course';
=======
import { Course } from './model/course';
import { CourseExtras } from './model/course-extras';
import { Injectable } from '@angular/core';
>>>>>>> 2ea01ee62fff1b675031c29d84f4e6e20e3f099f

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private readonly url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
  }

  public getFeatureFlagTest() {
    return this.http.get<string>(this.url + 'featureFlagTest');
  }

  public getAllCourses() {
    return this.http.get<Course[]>(this.url + 'courses');
  }

  public getCourse(id: string) {
    return this.http.get<Course>(this.url + 'course_edit', {
      params: new HttpParams().set('id', id)
    });
  }

  public saveCourse(course: Course) {
<<<<<<< HEAD
    return this.http.post<Course>(this.url + 'courses', course);
  }

  public getImpact(id: string) {
    return this.http.get(this.url + 'ImpactAssessment', {
      params: new HttpParams().set('requestId', id)
    });
=======
    return this.http.post<Course>(this.url + "courses", course);
>>>>>>> 2ea01ee62fff1b675031c29d84f4e6e20e3f099f
  }

  public submitEditedCourse(course: Course, courseExtras: CourseExtras) {
    console.log('would Post');
    return this.http.post(this.url + "save_request", {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    })
  }

}
