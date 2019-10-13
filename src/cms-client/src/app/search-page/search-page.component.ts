import {ChangeDetectorRef, Component, DoCheck, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

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
  filteredOptions: Observable<string[]>;
  displayedList: string[] = [];
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

  constructor(private ref: ChangeDetectorRef) { }

  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => value.length >= 1 ? this._filter(value) : [])
      );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    let returnedList: string[] = [];

    if (this.selectedValue === 'department') {
      returnedList = returnedList.concat(this.departments.filter(department => department.toLowerCase().includes(filterValue)));
      this.displayedList = returnedList;
    } else if (this.selectedValue === 'faculty' ) {
      // empty on purpose
    } else {
      returnedList = returnedList.concat(this.departments.filter(department => department.toLowerCase().includes(filterValue)));
      this.displayedList = returnedList;
    }

   /* switch (this.selectedValue) {
      case 'faculty': {
        // empty for now
        break;
      }
      case 'department': {
        returnedList = returnedList.concat(this.departments.filter(department => department.toLowerCase().includes(filterValue)));
        this.displayedList = returnedList;
        break;
      }
      case 'program': {
        // empty for now
        break;
      }
      case 'degree': {
        // empty for now
        break;
      }
      case 'course': {
        // empty for now
        break;
      }
      default: {
        returnedList = returnedList.concat(this.departments.filter(department => department.toLowerCase().includes(filterValue)));
        this.displayedList = returnedList;
        break;
      }
    }*/


    return returnedList;
  }

  public showResults(): void {
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

}

export class Department {
  name: string;
}
