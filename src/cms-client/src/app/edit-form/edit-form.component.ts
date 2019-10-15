import { Component, OnInit, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})

export class EditFormComponent implements OnInit, AfterViewInit {

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

  constructor() {
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    (document.querySelector('.ins') as HTMLElement).style.background = '#bbffbb';
    (document.querySelector('.del') as HTMLElement).style.background = '#ffbbbb';
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
