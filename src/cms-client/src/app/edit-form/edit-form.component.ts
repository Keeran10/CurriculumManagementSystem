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
  course: Course;

  model: Model = {
    department: 'COMP',
    number: '228',
    courseName: 'System Hardware',
    credits: '3',
    prerequisites: 'COMP 248; MATH 203 or Cegep Mathematics 103 or NYA previously or concurrently;'
                    + 'MATH 204 or Cegep Mathematics 105 or NYC previously or concurrently.',
    description: 'Levels of system abstraction and von Neumann model. Basics of digital logic design.'
                 + 'Data representation and manipulation. Instruction set architecture. Processor internals.'
                 + 'Assembly language programming. Memory subsystem and cache management. I/O subsystem.'
                 + 'Introduction to network organization and architecture. Lectures: three hours per week.'
                 + 'Tutorial: two hours per week.',
    notes: 'Students who have received credit for SOEN 228 may not take this course for credit.'
  };

  original: Model = {
    department: 'COMP',
    number: '228',
    courseName: 'System Hardware',
    credits: '3',
    prerequisites: 'COMP 248; MATH 203 or Cegep Mathematics 103 or NYA previously or concurrently;'
                    + 'MATH 204 or Cegep Mathematics 105 or NYC previously or concurrently.',
    description: 'Levels of system abstraction and von Neumann model. Basics of digital logic design.'
                 + 'Data representation and manipulation. Instruction set architecture. Processor internals.'
                 + 'Assembly language programming. Memory subsystem and cache management. I/O subsystem.'
                 + 'Introduction to network organization and architecture. Lectures: three hours per week.'
                 + 'Tutorial: two hours per week.',
    notes: 'Students who have received credit for SOEN 228 may not take this course for credit.'
  };

  constructor(private route: ActivatedRoute, private api: ApiService) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    this.api.getCourse(this.id).subscribe(data => this.course = data);
    console.log(this.course);
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
}

export class Model {
  department: string;
  number: string;
  credits: string;
  courseName: string;
  prerequisites: string;
  description: string;
  notes: string;
}
