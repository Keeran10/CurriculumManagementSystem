// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

import { HttpClient, HttpParams } from '@angular/common/http';
import { Course } from './models/course';
import { CourseExtras } from './models/course-extras';
import { Injectable } from '@angular/core';
import { Package } from './models/package';
import { User } from './models/user';

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


  public setCredentials(email: string, password: string) {
    return this.http.get<User>(this.url + 'login', {
      params: new HttpParams().set('email', email).set('password', password)
    });
  }

  public getImpact(course: Course, courseExtras: CourseExtras) {

    console.log('Impact endpoint called.');

    return this.http.post(this.url + 'get_impact', {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    });
  }

  public submitEditedCourse(course: Course, courseExtras: CourseExtras) {
    console.log(course);
    console.log(courseExtras);
    return this.http.post(this.url + "save_request", {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    });
  }

  public savePipeline(pipeline: string, packageId: any) {
    console.log('set approval pipeline');
    return this.http.post(this.url + 'setApprovalPipeline', {
      params: new HttpParams().set('approval_pipeline', pipeline)
        .set('package_id', packageId)
    });
  }

  public getApprovalPipeline(pipelineId: any) {
    return this.http.get<string[]>(this.url + 'approvalPipeline', {
      params: new HttpParams().set('approval_pipeline_id', pipelineId)
    });
  }

  public getCurrentPosition(packageId: string, approvalPipelineId: string) {
    return this.http.get<any>(this.url + 'approvalPipelinePosition', {
      params: new HttpParams().set('package_id', packageId).set('approval_pipeline_id', approvalPipelineId),
      responseType: 'arraybuffer' as 'json'
    });
  }

  public generatePdf(packageId: string) {
    return this.http.get<boolean>(this.url + 'generate_pdf', {
      params: new HttpParams().set('package_id', packageId)
    });
  }

  public viewPdf(packageId: string) {
    window.open();
    return this.http.get<BlobPart>(this.url + 'get_pdf', {
      params: new HttpParams().set('package_id', packageId),
      responseType: 'arraybuffer' as 'json'
    });
  }

  public getAllPackages(departmentId: string) {
    return this.http.get<Package[]>(this.url + 'get_packages', {
      params: new HttpParams().set('department_id', departmentId)
    });
  }

  public getPackage(packageId: string, departmentId: string) {
    return this.http.get<Package>(this.url + 'get_package', {
      params: new HttpParams().set('package_id', packageId)
        .set('department_id', departmentId)
    });
  }

  public getPipeline(packageId: any) {

    console.log("api-getPipeline:" + packageId);
    return this.http.get<any>(this.url + 'get_pipeline', {
      params: new HttpParams().set('package_id', packageId),
      responseType: 'arraybuffer' as 'json'
    });

  }

}
