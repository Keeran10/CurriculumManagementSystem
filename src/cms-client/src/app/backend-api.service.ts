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

import { HttpClient, HttpParams, HttpRequest, HttpEvent } from '@angular/common/http';
import { Course } from './models/course';
import { CourseExtras } from './models/course-extras';
import { Degree } from './models/degree';
import { Department } from './models/department';
import { Faculty } from './models/faculty';
import { Injectable } from '@angular/core';
import { Package } from './models/package';
import { Program } from './models/program';
import { Revision } from './models/revision';
import { User } from './models/user';
import { PipelineRevisions } from './models/pipeline-revisions';
import { SupportingDocument } from './models/supporting-document';
import { Section } from './models/section';
import { SectionExtras } from './models/section-extras';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private readonly url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
    // this.url = 'http://192.168.99.100:8080/';
  }

  public getFeatureFlagTest() {
    return this.http.get<string>(this.url + 'featureFlagTest');
  }

  public getAllCourses() {
    return this.http.get<Course[]>(this.url + 'courses');
  }

  public getAllDegrees() {
    return this.http.get<Degree[]>(this.url + 'degrees');
  }

  public getAllDepartments() {
    return this.http.get<Department[]>(this.url + 'departments');
  }

  public getAllFaculties() {
    return this.http.get<Faculty[]>(this.url + 'faculties');
  }

  public getAllPrograms() {
    return this.http.get<Program[]>(this.url + 'programs');
  }

  public getAllSections() {
    // this returns all sections
    return this.http.get<Section[]>(this.url + 'sections');
  }

  public getSectionsByDepartment(department_id: any) {
    return this.http.get<Section>(this.url + 'fetch_all_sections', {
      params: new HttpParams().set('department_id', department_id)
    });
  }

  public getCourse(id: string) {
    return this.http.get<Course>(this.url + 'course_edit', {
      params: new HttpParams().set('id', id)
    });
  }


  public saveCourse(course: Course) {
    return this.http.post<Course>(this.url + 'courses', course);
  }

  // for future implementation when we have all sections
  public saveSection(section: Section) {
    return this.http.post<Section>(this.url + 'sections', section);
  }


  public setCredentials(email: string, password: string) {
    return this.http.get<User>(this.url + 'login', {
      params: new HttpParams().set('email', email).set('password', password)
    });
  }

  public getImpact(course: Course, courseExtras: CourseExtras) {

    console.log('Impact endpoint called.');

    return this.http.post(this.url + 'ImpactAssessment', {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    });
  }

  /*
  public submitEditedCourse(course: Course, courseExtras: CourseExtras) {
    console.log(course);
    console.log(courseExtras);
    return this.http.post(this.url + 'save_request', {
      params: new HttpParams().set('course', JSON.stringify(course))
        .set('courseExtras', JSON.stringify(courseExtras))
    });
  }
  */

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

  public getCalendar() {
    return this.http.get<any>(this.url + 'section71709');
  }

  public getSectionData(id: string) {
    // this returns this one section
    return this.http.get<Section>(this.url + 'section' + id);
  }

/*  public getSection(id: string) {
    // this returns this one section
    return this.http.get<Section>(this.url + 'section71709');
  }*/

  public generatePdf(packageId: string, userId: string) {
    return this.http.get<boolean>(this.url + 'generate_pdf', {
      params: new HttpParams().set('package_id', packageId).set('user_id', userId)
    });
  }

  public viewPdf(packageId: string) {
    // window.open();
    return this.http.get<BlobPart>(this.url + 'get_pdf', {
      params: new HttpParams().set('package_id', packageId),
      responseType: 'arraybuffer' as 'json'
    });
  }

  public viewPdfFromPackagePage(packageId: string, userId: string) {
    // window.open();
    return this.http.get<BlobPart>(this.url + 'get_pdf_packagePage', {
      params: new HttpParams().set('package_id', packageId).set('user_id', userId),
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

    console.log('api-getPipeline:' + packageId);
    return this.http.get<any>(this.url + 'get_pipeline', {
      params: new HttpParams().set('package_id', packageId),
      responseType: 'arraybuffer' as 'json'
    });

  }

  public setApprovalStatus(userId: any, packageId: any, pipelineId: any, rationale: any, isApproved: any) {
    console.log('Changing status of package');
    const formdata: FormData = new FormData();
    formdata.append('user_id', userId);
    formdata.append('package_id', packageId);
    formdata.append('approval_pipeline_id', pipelineId);
    formdata.append('rationale', rationale);
    formdata.append('is_approved', isApproved);
    const req = new HttpRequest('POST', this.url + 'validatePackage', formdata, {
      reportProgress: true,
      responseType: 'text',
    });
    return this.http.request(req);
  }

  public uploadFile(files: File[], descriptions: Map<string, string>, packageId: any, userId: any) {
    const formdata: FormData = new FormData();

    for (const file of files) {
      formdata.append('files', file);
    }

    formdata.append('descriptions', JSON.stringify(Array.from(descriptions.entries())));
    formdata.append('package_id', packageId);
    formdata.append('user_id', userId);

    const req = new HttpRequest('POST', this.url + 'upload_files', formdata, {
      reportProgress: true,
      responseType: 'text',
    });

    return this.http.request(req);
  }

  public submitCourseRequestForm(files: File[], descriptions: Map<string, string>,
    course: Course, courseExtras: CourseExtras) {

    const formdata: FormData = this.fileCourseAndExtrasToFormData(files, descriptions, course, courseExtras);

    const req = new HttpRequest('POST', this.url + 'save_request', formdata, {
      reportProgress: true,
      responseType: 'text',
    });

    return this.http.request(req);
  }

  public submitCalendarSectionForm(files: File[], descriptions: Map<string, string>,
    section: Section, sectionExtras: SectionExtras) {

    const formdata: FormData = this.submitSection(files, descriptions, section, sectionExtras);

    const req = new HttpRequest('POST', this.url + 'save_section70719', formdata, {
      reportProgress: true,
      responseType: 'text',
    });
    return this.http.request(req);
  }

  public getRevisions(packageId: any) {
    console.log('api-getRevisions ' + packageId);
    return this.http.get<Revision[]>(this.url + 'dossier_revisions', {
      params: new HttpParams().set('id', packageId)
    });
  }

  public getRevisionsPdf(revId: any) {
    return this.http.get<BlobPart>(this.url + 'get_rev_pdf', {
      params: new HttpParams().set('rev_id', revId),
      responseType: 'arraybuffer' as 'json'
    });
  }

  public submitDeleteCourseRequestForm(files: File[], descriptions: Map<string, string>, course: Course, courseExtras: CourseExtras) {
    const formdata: FormData = this.fileCourseAndExtrasToFormData(files, descriptions, course, courseExtras);

    const req = new HttpRequest('POST', this.url + 'save_removal_request', formdata, {
      reportProgress: true,
      responseType: 'text',
    });

    return this.http.request(req);
  }

  private fileCourseAndExtrasToFormData(files: File[], descriptions: Map<string, string>, course: Course, courseExtras: CourseExtras) {
    const formdata: FormData = new FormData();
    formdata.append('descriptions', JSON.stringify(Array.from(descriptions.entries())));
    formdata.append('course', JSON.stringify(course));
    formdata.append('courseExtras', JSON.stringify(courseExtras));
    for (const file of files) {
      formdata.append('files', file);
    }

    return formdata;
  }

  private submitSection(files: File[], descriptions: Map<string, string>, section: Section, sectionExtras: SectionExtras) {
    const formdata: FormData = new FormData();
    formdata.append('descriptions', JSON.stringify(Array.from(descriptions.entries())));
    formdata.append('subSection70719', JSON.stringify(section));
    formdata.append('sectionExtras', JSON.stringify(sectionExtras));
    for (const file of files) {
      formdata.append('files', file);
    }

    return formdata;
  }

  public getPackagesToBeApproved(userType: string) {
    return this.http.get<Package[]>(this.url + 'get_packages_by_type', {
      params: new HttpParams().set('userType', userType)
    });
  }

  public getEditKey(packageId: any) {
    console.log('api-getEditKey ' + packageId);
    return this.http.get<any>(this.url + 'get_edit_key', {
      params: new HttpParams().set('package_id', packageId)
    });
  }

  public getReviewKey(packageId: any) {
    console.log('api-getReviewKey ' + packageId);
    return this.http.get<any>(this.url + 'get_review_key', {
      params: new HttpParams().set('package_id', packageId)
    });
  }

  public releaseEditKey(packageId: any) {
    console.log('api-releaseEditKey ' + packageId);
    return this.http.get<any>(this.url + 'release_edit_key', {
      params: new HttpParams().set('package_id', packageId)
    });
  }

  public releaseReviewKey(packageId: any) {
    console.log('api-releaseReviewKey ' + packageId);
    return this.http.get<any>(this.url + 'release_review_key', {
      params: new HttpParams().set('package_id', packageId)
    });
  }

  public isMutexAvailable(packageId: any) {
    console.log('api-isMutexAvailable ' + packageId);
    return this.http.get<any>(this.url + 'is_mutex_available', {
      params: new HttpParams().set('package_id', packageId)
    });
  }

  public getPipelineAudit(pipelineId: any) {
    console.log('api-getPipelineRevisions ' + pipelineId);
    return this.http.get<PipelineRevisions[]>(this.url + '/pipeline_revisions', {
      params: new HttpParams().set('pipeline_id', pipelineId)
    });
  }

  public getSupportingDocuments(target_id: any, target_type: any) {
    console.log('api-getSupportingDocuments ' + target_id + target_type);
    return this.http.get<SupportingDocument[]>(this.url + 'get_supporting_documents', {
      params: new HttpParams().set('target_id', target_id).set('target_type', target_type)
    });
  }

  public getSupportingDocumentPdf(file_id: any) {
    return this.http.get<BlobPart>(this.url + 'get_supporting_document_pdf', {
      params: new HttpParams().set('file_id', file_id),
      responseType: 'arraybuffer' as 'json'
    });
  }

  public removeSupportingDocument(file_id: any) {
    return this.http.get<string>(this.url + 'remove_supporting_document', {
      params: new HttpParams().set('file_id', file_id)
    });
  }

  public removeRequest(request_id: any) {
    return this.http.get<string>(this.url + 'delete_request', {
      params: new HttpParams().set('requestId', request_id)
    });
  }

  public getDegreeRequirements(department_id) {
    return this.http.get<any>(this.url + 'get_degrees_by_department', {
      params: new HttpParams().set('department_id', department_id)
    });
  }

  public registerUser(first: any, last: any, type: any, email: any, pass: any, depId: any) {
    const formdata: FormData = new FormData();

    formdata.append('first_name', first);
    formdata.append('last_name', last);
    formdata.append('user_type', type);
    formdata.append('email', email);
    formdata.append('password', pass);
    formdata.append('department_id', depId);

    const req = new HttpRequest('POST', this.url + 'register_user', formdata, {
      reportProgress: true,
      responseType: 'text',
    });

    return this.http.request(req);
  }
}
