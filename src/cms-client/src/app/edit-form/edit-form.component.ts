import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { Course } from '../models/course';
import { Component, ViewChild } from '@angular/core';
import { CourseExtras } from '../models/course-extras';
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

  constructor(private route: ActivatedRoute, private api: ApiService) {
  }

  // tslint:disable-next-line:use-lifecycle-interface
  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    this.api.getCourse(this.id).subscribe(data => {
      console.log(data);
      this.courseOriginal = data;
      this.courseEditable = Object.assign({}, data);
      this.setRequisitesStrings(data);
      this.editedModel = Object.assign({}, this.model);
    });
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

  public setRequisitesStrings(course: Course) {
    let isNextEquivalent = false;
    if (course.requisites.length > 0) {
      course.requisites.forEach(r => {
        switch (r.type) {
          case 'equivalent':
            if (!isNextEquivalent) {
              this.model.equivalents += r.name + r.number + ' or ';
            } else {
              this.model.equivalents += r.name + r.number + '; ';
            }
            isNextEquivalent = !isNextEquivalent;
            break;
          case 'prerequisite':
            this.model.prerequisites += r.name + r.number + '; ';
            break;
          case 'corequisite':
            this.model.corequisites += r.name + r.number + '; ';
            break;
        }
      });
    }
  }

  // There have been some backend changes concerning these fields. Will uncomment them and complete implementation later.
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
    this.api.submitEditedCourse(this.courseEditable, this.editedModel)
      .subscribe(data => { console.log(data) })
  }
}
