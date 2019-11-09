import { HttpClient, HttpParams } from '@angular/common/http';
import { Course } from './model/course';
import { CourseExtras } from './model/course-extras';
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
    return this.http.post(this.url + "save_request", {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    })
  }

  public savePipeline(pipeline: string[], id: any) {
    console.log('set approval pipeline');
    return this.http.post(this.url + 'setApprovalPipeline', {
      params: new HttpParams().set('approval_pipeline', JSON.stringify(pipeline))
        .set('package_id', id)
    });
  }

}
