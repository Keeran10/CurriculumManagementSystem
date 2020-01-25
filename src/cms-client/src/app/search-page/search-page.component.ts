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

import { ApiService } from '../backend-api.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../models/course';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Degree } from '../models/degree';
import { Faculty } from '../models/faculty';
import { Program } from '../models/program';
import { Department } from '../models/department';
import { Section } from '../models/section';


export interface SearchCategory {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {
  myControl = new FormControl();
  myChildControl = new FormControl();
  filteredOptions: Observable<string[]>;

  displayedList: string[] = [];
  descriptionList: string [] = [];

  storedCourseNames: string[] = [];
  storedDegreeNames: string[] = [];
  storedDepartmentNames: string[] = [];
  storedProgramNames: string[] = [];
  storedFacultyNames: string[] = [];
  storedSectionNames: string[] = [];

  courses: Course[];
  degrees: Degree[];
  faculties: Faculty[];
  programs: Program[];
  departments: Department[];
  sections: Section[];

  isResultShown = false;
  searchFormPlaceholder = 'Select Search Category';

  selectedValue: string;
  searchType: string;

  searchCategories: SearchCategory[] = [
    {value: 'faculty', viewValue: 'Faculty'},
    {value: 'department', viewValue: 'Department'},
    {value: 'program', viewValue: 'Program'},
    {value: 'degree', viewValue: 'Degree'},
    {value: 'course', viewValue: 'Course'},
    {value: 'section', viewValue: 'Section'},
  ];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.setFilteredOptions();
    this.apiService.getAllCourses().subscribe(data => {
      this.courses = data;
    });
    this.apiService.getAllDegrees().subscribe(data => {
      this.degrees = data;
    });
    this.apiService.getAllDepartments().subscribe(data => {
      this.departments = data;
    });
    this.apiService.getAllFaculties().subscribe(data => {
      this.faculties = data;
    });
    this.apiService.getAllPrograms().subscribe(data => {
      this.programs = data;
    });
    this.apiService.getAllSections().subscribe(data => {
      this.sections = data;
    });
  }

  private setFilteredOptions() {
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => {
          return value.length >= 1 ? this._filter(value) : [];
        })
      );
  }

  private getStringValue() {
    this.courses.forEach(value => this.storedCourseNames.
    push(value.name + ' ' + value.number + ' ' + value.title));
    this.degrees.forEach(value => this.storedDegreeNames.
    push(value.name + ' '));
    this.departments.forEach(value => this.storedDepartmentNames.
    push(value.name + ' '));
    this.faculties.forEach(value => this.storedFacultyNames.
    push(value.name + ' '));
    this.programs.forEach(value => this.storedProgramNames.
    push(value.name + ' '));
    this.sections.forEach(value => this.storedSectionNames.
    push(value.title + ' '));

  }

  private getDescription() {
    // test function
    this.courses.forEach(value => this.descriptionList.push(value.description));
    this.degrees.forEach(value => this.descriptionList.push(value.degreeRequirements.toString()));
    this.departments.forEach(value => this.descriptionList.push(value.programs.toString()));
    this.faculties.forEach(value => this.descriptionList.push(value.departments.toString()));
    this.programs.forEach(value => this.descriptionList.push(value.description));
    this.sections.forEach(value => this.descriptionList.push(value.firstParagraph));

    this.descriptionList = this.descriptionList.filter((el, i, a) => i === a.indexOf(el));
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase().trim()
      .replace(/\s/g, '');
    let returnedList: string[] = [];
    this.getStringValue();
    this.getDescription();

    switch (this.selectedValue) {
      case 'faculty': {
        returnedList = returnedList.concat(this.storedFacultyNames.filter(c => c
          .toLowerCase()
          .trim()
          .replace(/\s/g, '')
          .includes(filterValue)));
        const filteredList = returnedList;
        this.displayedList = filteredList.filter((el, i, a) => i === a.indexOf(el));
        this.isResultShown = false;
        this.searchType = 'faculty';
        break;
      }
      case 'department': {
        returnedList = returnedList.concat(this.storedDepartmentNames.filter(c => c
          .toLowerCase()
          .trim()
          .replace(/\s/g, '')
          .includes(filterValue)));
        const filteredList = returnedList;
        this.displayedList = filteredList.filter((el, i, a) => i === a.indexOf(el));
        this.isResultShown = false;
        this.searchType = 'department';
        break;
      }
      case 'program': {
        returnedList = returnedList.concat(this.storedProgramNames.filter(c => c
          .toLowerCase()
          .trim()
          .replace(/\s/g, '')
          .includes(filterValue)));
        const filteredList = returnedList;
        this.displayedList = filteredList.filter((el, i, a) => i === a.indexOf(el));
        this.isResultShown = false;
        this.searchType = 'program';
        break;
      }
      case 'degree': {
        returnedList = returnedList.concat(this.storedDegreeNames.filter(c => c
          .toLowerCase()
          .trim()
          .replace(/\s/g, '')
          .includes(filterValue)));
        const filteredList = returnedList;
        this.displayedList = filteredList.filter((el, i, a) => i === a.indexOf(el));
        this.isResultShown = false;
        this.searchType = 'degree';
        break;
      }
      case 'course': {
        returnedList = returnedList.concat(this.storedCourseNames.filter(c => c
          .toLowerCase()
          .trim()
          .replace(/\s/g, '')
          .includes(filterValue)));
        const filteredList = returnedList;
        this.displayedList = filteredList.filter((el, i, a) => i === a.indexOf(el));
        this.isResultShown = false;
        this.searchType = 'course';
        break;
      }
      case 'section': {
        returnedList = returnedList.concat(this.storedSectionNames.filter(c => c
          .toLowerCase()
          .trim()
          .replace(/\s/g, '')
          .includes(filterValue)));
        const filteredList = returnedList;
        this.displayedList = filteredList.filter((el, i, a) => i === a.indexOf(el));
        this.isResultShown = false;
        this.searchType = 'section';
        break;
      }
      default: {
        // Need to add default to return everything from all categories
        returnedList = [];
        this.displayedList = returnedList;
        returnedList = [];
        break;
      }
    }

    return returnedList;
  }

  public showResults(): void {
    // this.myControl.updateValueAndValidity(); // might want to use this later
    this.isResultShown = false;
    this.myControl.reset(); // resets whatever was written,
    this.setFilteredOptions();
    this.isResultShown = true;
  }

  public selectSearchFormPlaceholder(): void {
    switch (this.selectedValue) {
      case 'faculty': {
        this.searchFormPlaceholder = 'Search faculties';
        break;
      }
      case 'department': {
        this.searchFormPlaceholder = 'Search departments';
        break;
      }
      case 'program': {
        this.searchFormPlaceholder = 'Search programs';
        break;
      }
      case 'degree': {
        this.searchFormPlaceholder = 'Search degrees';
        break;
      }
      case 'course': {
        this.searchFormPlaceholder = 'Search courses';
        break;
      }
      case 'section': {
        this.searchFormPlaceholder = 'Search calendar sections';
        break;
      }
      default: {
        this.searchFormPlaceholder = 'Select Search Category';
        break;
      }
    }

  }

  public getCourseId(listItem: string) {
    const tmpCourse: Course = this.courses.find(c => listItem.includes(c.title));
    return tmpCourse.id;
  }

  public getDepartmentId(listItem: string) {
    const tmpDepartment: Department = this.departments.find(c => listItem.includes(c.name));
    return tmpDepartment.id;
  }

  public getDegreeId(listItem: string) {
    const tmpDegree: Degree = this.degrees.find(c => listItem.includes(c.name));
    return tmpDegree.id;
  }

  public getFacultyId(listItem: string) {
    const tmpFaculty: Faculty = this.faculties.find(c => listItem.includes(c.name));
    return tmpFaculty.id;
  }

  public getProgramId(listItem: string) {
    const tmpProgram: Program = this.programs.find(c => listItem.includes(c.name));
    return tmpProgram.id;
  }

  public getSectionId(listItem: string) {
    const tmpSection: Section = this.sections.find(c => listItem.includes(c.title));
    return tmpSection.id;
  }

}
