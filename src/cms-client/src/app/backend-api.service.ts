import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Course } from './model/course';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
  }

  public getFeatureFlagTest() {
    return this.http.get<string>(this.url + 'featureFlagTest');
  }

  public getAllCourses(){
    return this.http.get<Course[]>(this.url + 'courses');
  }

  public getCourse(id: string) {
    return this.http.get<Course>(this.url + 'course/',{
      params: new HttpParams().set('id', id)
  });
  }
}
