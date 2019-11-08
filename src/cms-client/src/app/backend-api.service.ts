import { HttpClient, HttpParams } from '@angular/common/http';
import { Course } from './model/course';
import { CourseExtras } from './edit-form/edit-form.component';
import { Injectable } from '@angular/core';

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
    return this.http.post<Course>(this.url + "courses", course);
  }

  public submitEditedCourse(course: Course, courseExtras: CourseExtras) {
    console.log('would Post');
<<<<<<< HEAD
    return this.http.post(this.url + "save_request", {
=======
    // The following code is commented out as the backend route has yet to be implemented. This will be changed to meet the implementation but
    // provides a general idea of what the call would look like.
    /*return this.http.post(this.url + urlPath,{
>>>>>>> 2f8f8171a62a4e0e00642b5b41ff592703786ca3
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    })

  }

}
