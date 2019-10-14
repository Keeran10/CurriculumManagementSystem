import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Course } from '../model/course';
import { Observable } from 'rxjs';

@Injectable()
export class CourseService {

  private coursesUrl: string;

  constructor(private http: HttpClient) {
    this.coursesUrl = 'http://localhost:8080/courses';
  }

  public findAll(): Observable<Course[]> {
    return this.http.get<Course[]>(this.coursesUrl);
  }

  public save(course: Course) {
    return this.http.post<Course>(this.coursesUrl, course);
  }
}
