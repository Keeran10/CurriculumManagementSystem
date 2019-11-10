import { HttpClient, HttpParams } from '@angular/common/http';
import { Course } from './models/course';
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
    return this.http.post<Course>(this.url + 'courses', course);
  }

  public getImpact(course: Course, courseExtras: CourseExtras) {

    console.log('Impact endpoint called.');

    return this.http.post(this.url + 'get_impact', {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    });
  }

  public submitEditedCourse(course: Course, courseExtras: CourseExtras) {
    console.log('would Post');
    return this.http.post(this.url + 'save_request', {
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

  public generatePdf(packageId: string){
    return this.http.get<boolean>(this.url + 'generate_pdf', {
      params: new HttpParams().set('package_id', packageId)
    });
  }

  public viewPdf(packageId: string){
    return this.http.get<BlobPart>(this.url + 'get_pdf', {
      params: new HttpParams().set('package_id', packageId),
      responseType: 'arraybuffer' as 'json'
    });
  }

}
