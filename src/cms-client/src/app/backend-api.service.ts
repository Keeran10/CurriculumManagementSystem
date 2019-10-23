import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from './model/course';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://192.168.99.100:8080/';
  }

  public getFeatureFlagTest() {
    return this.http.get<string>(this.url + 'featureFlagTest');
  }

  public getCourse(id: string) {
    return this.http.get<Course>(this.url + 'courses/' + id);
  }
}
