import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { Course } from '../models/course';
import { Component, ViewChild } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { CourseExtras } from '../model/course-extras';
import { SupportDocumentComponent } from '../support-documents/support-documents.component';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})

export class EditFormComponent {

  @ViewChild(SupportDocumentComponent, { static: false })
  supportDocumentComponent: SupportDocumentComponent;

  id: string;
  courseOriginal: Course = new Course();
  courseEditable: Course = new Course();

  model = new CourseExtras();
  editedModel = new CourseExtras();

  constructor(private route: ActivatedRoute, private api: ApiService, private cookieService: CookieService) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    let requestId = this.cookieService.get('request');
    let packageId = this.cookieService.get('package');
    //let UserId = this.cookieService.get('user');
    this.model.packageId = Number(packageId);
    this.editedModel.packageId = Number(packageId);
    if(requestId === '0'){
      this.api.getCourse(this.id).subscribe(data => {
        this.courseOriginal = data;
        this.courseEditable = Object.assign({}, data);
        this.setRequisitesStrings(data, this.model);
        this.setRequisitesStrings(data, this.editedModel)
      });
    }
    else {
      let originalId = this.cookieService.get('originalCourse');
      let editedId = this.cookieService.get('editedCourse');
      this.api.getCourse(originalId).subscribe(data => {
        this.courseOriginal = data;
        this.setRequisitesStrings(data, this.model);
      });
      this.api.getCourse(editedId).subscribe(data => {
        this.courseEditable = data;
        this.setRequisitesStrings(data, this.editedModel);
      });
    }
  }

  public highlightChanges(): void {
    const insElements = document.querySelectorAll('ins');
    const delElements = document.querySelectorAll('del');

    insElements.forEach((e) => {
      e.style.background = '#bbffbb';
    });

    delElements.forEach((e) => {
      e.style.background = '#ffbbbb';
    });
  }

  public setRequisitesStrings(course: Course, courseExtras: CourseExtras) {
    let isNextEquivalent = false;
    if (course.requisites.length > 0) {
      course.requisites.forEach(r => {
        switch (r.type) {
          case 'equivalent':
            if(!isNextEquivalent){
              courseExtras.equivalents += r.name + r.number + " or ";
            }
            else{
              courseExtras.equivalents += r.name + r.number + '; '; 
            }
            isNextEquivalent = !isNextEquivalent;
            break;
          case 'prerequisite':
            courseExtras.prerequisites += r.name + r.number + '; '; 
            break;
          case 'corequisite':
            courseExtras.corequisites += r.name + r.number + '; ';
            break; 
        }
      })
    }
  }

  //There have been some backend changes concerning these fields. Will uncomment them and complete implementation later.
  /*
  public setDegreesStrings(course: Course) {
    if(course.degreeRequirements.length > 0){
      course.degreeRequirements.forEach(d => {
        if(d.type === 'degree'){
          do x
        }
        else if(d.type === 'elective'){
          do y
        }
      })
    }
  */

  public submitForm() {
    this.editedModel.files = this.supportDocumentComponent.documents;
    this.api.submitEditedCourse(this.courseEditable, this.editedModel).subscribe(data => console.log(data))
  }
}