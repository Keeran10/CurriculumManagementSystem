import { ApiService } from '../backend-api.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';



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
  courses: Course[];
  isResultShown = false;
  searchFormPlaceholder = 'Select Search Category';

  departments: string[] = [
    'Software Engineering',
    'Computer Science',
    // test value.
    'AAA'
  ];

  selectedValue: string;

  searchCategories: SearchCategory[] = [
    {value: 'faculty', viewValue: 'Faculty'},
    {value: 'department', viewValue: 'Department'},
    {value: 'program', viewValue: 'Program'},
    {value: 'degree', viewValue: 'Degree'},
    {value: 'course', viewValue: 'Course'}
  ];

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.setFilteredOptions();
    this.apiService.getAllCourses().subscribe(data => {
      this.courses = data;
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
  }

  private getDescription() {
    // test function
    this.courses.forEach(value => this.descriptionList.push(value.description));
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
        // this behavior is the default for now to reset the displayedList
        returnedList = [];
        this.displayedList = returnedList;
        // removes the previous displayed list when user starts typing
        this.isResultShown = false;
        break;
      }
      case 'department': {
         returnedList = returnedList.concat(this.departments.filter(department => department.toLowerCase().includes(filterValue)));
         this.displayedList = returnedList;
        // returnedList = []; // if we put this, auto-completion gets removed
         this.isResultShown = false;
         break;
      }
      case 'program': {
        // this behavior is the default for now to reset the displayedList
        returnedList = [];
        this.displayedList = returnedList;
        returnedList = [];
        break;
      }
      case 'degree': {
        // this behavior is the default for now to reset the displayedList
        returnedList = [];
        this.displayedList = returnedList;
        returnedList = [];
        break;
      }
      case 'course': {
        // this behavior is the default for now to reset the displayedList
        returnedList = returnedList.concat(this.storedCourseNames.filter(c => c
          .toLowerCase()
          .trim()
          .replace(/\s/g, '')
          .includes(filterValue)));
        const filteredList = returnedList;
        this.displayedList = filteredList.filter((el, i, a) => i === a.indexOf(el));
        this.isResultShown = false;
        break;
      }
      default: {
        returnedList = returnedList.concat(this.departments.filter(department => department.toLowerCase().includes(filterValue)));
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
      default: {
        this.searchFormPlaceholder = 'Select Search Category';
        break;
      }
    }

  }

  public getCourseId(listItem: string){
    let tmpCourse: Course = this.courses.find(c => listItem.includes(c.title));
    return tmpCourse.id;
  }

}

export class Department {
  name: string;
}
