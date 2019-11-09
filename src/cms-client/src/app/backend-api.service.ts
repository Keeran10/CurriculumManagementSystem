import { HttpClient, HttpParams } from '@angular/common/http';
import { Course } from './model/course';
import { CourseExtras } from './model/course-extras';
import { Injectable } from '@angular/core';
import { Package } from './model/package';

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
    return this.http.post(this.url + "save_request", {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    })
  }

  public getAllPackages(departmentId: string) {
    return this.http.get<Package[]>(this.url + 'get_packages', {
      params: new HttpParams().set('department_id', departmentId)
    });
  }

  public getPackage(packageId: string, departmentId: string){
    return this.http.get<Package>(this.url + 'get_package', {
      params: new HttpParams().set('package_id', packageId)
        .set('department_id', departmentId)
    });
  }

}
