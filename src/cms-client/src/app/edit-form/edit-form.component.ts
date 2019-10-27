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
    prerequisites: ''
  };

  editedModel = {
    prerequisites: ''
  }

  constructor(private route: ActivatedRoute, private api: ApiService) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    this.api.getCourse(this.id).subscribe(data =>{
      console.log(data);
      this.courseOriginal = data;
      this.courseEditable = Object.assign({}, data);
      this.model.prerequisites = this.getPrerequisitesString(data);
      this.editedModel = Object.assign({}, this.model);
    });
  }

  ngAfterViewInit(){

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

  public getPrerequisitesString(course:Course): string{
    let result = '';
    if(course.prerequisites.length > 0){
      course.prerequisites.forEach(p => result += p + "; ")
    }
    return result;
  }

  public parsePrerequisitesString(prerequisites: string): Object{
    return {};
  }
}
