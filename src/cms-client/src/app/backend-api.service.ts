import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Course } from './model/course';

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
    return this.http.post<Course>(this.url + 'courses', course);
  }

  public getRequest(requestId: string) {
    return this.http.get<Map<string, object>>(this.url + 'ImpactAssessment', {
      params: new HttpParams().set('requestId', requestId)
    });
  }
}
