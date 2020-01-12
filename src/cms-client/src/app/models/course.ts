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

import { Requisite } from '../models/requisite';
import { Program } from './program';

export class Course {
  id: number;
  credits: number;
  degreeRequirements: Object[];
  description: string;
  equivalent: string[];
  isActive: boolean;
  labHours: number;
  lectureHours: number;
  level: number;
  name: string;
  note: string;
  number: number;
  program: Object;
  requisites: Requisite[];
  title: string;
  tutorialHours: number;

  constructor(){
    this.id = 0;
    this.credits = 0;
    this.degreeRequirements = [];
    this.description = '';
    this.equivalent = [];
    this.isActive = false;
    this.labHours = 0;
    this.lectureHours = 0;
    this.level = 1;
    this.name = '';
    this.note = '';
    this.number = 100;
    this.program = new Program();
    this.requisites = [];
    this.title = '';
    this.tutorialHours = 0;
  }
}
