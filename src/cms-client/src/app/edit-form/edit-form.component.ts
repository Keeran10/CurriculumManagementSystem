import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { Course } from '../model/course';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})

export class EditFormComponent implements OnInit {

  id: string;
  courseOriginal: Course = new Course();
  courseEditable: Course = new Course();

  model = {
    prerequisites: '',
    corequisites: '',
    antirequisites: '',
    rationale: '',
    implications: ''
  };

  editedModel = {
    prerequisites: '',
    corequisites: '',
    antirequisites: '',
    rationale: '',
    implications: ''
  }

  constructor(private route: ActivatedRoute, private api: ApiService) {
  }

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

  ngAfterViewInit() {

  }

  public highlightChanges(): void {
    const insElements = document.querySelectorAll('ins');
    const delElements = document.querySelectorAll('del');

    insElements.forEach( (e) => {
      e.style.background = '#bbffbb';
    });

    delElements.forEach( (e) => {
      e.style.background = '#ffbbbb';
    });
  }

  public setRequisitesStrings(course: Course) {
    if(course.requisites.length > 0){
      course.requisites.forEach(r => {
        if(r.type === 'prerequisite'){
          this.model.prerequisites += r.name + r.number + '; '; 
        }
        else if(r.type === 'corequisite'){
          this.model.corequisites += r.name + r.number + '; '; 
        }
      })
    }
  }
/*
  public setDegreesStrings(course: Course) {
    if(course.degreeRequirements.length > 0){
      course.degreeRequirements.forEach(d => {
        if(d.type === 'prerequisite'){
          this.model.prerequisites += p.name + p.number + '; '; 
        }
        else if(p.type === 'corequisite'){
          this.model.corequisites += p.name + p.number + '; '; 
        }
      })
    }
  }
*/
  public parsePrerequisitesString(prerequisites: string): Object{
    return {};
  }

  public submitForm(){
    
  }
}
