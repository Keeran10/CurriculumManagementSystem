import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
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

  departments: string[] = [
    'Software Engineering',
    'Computer Science',
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


  myControl = new FormControl();
  filteredOptions: Observable<string[]>;
  displayedList: string[] = [];
  isResultShown = false;

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
    } else if (this.selectedValue === 'faculty' ) {
      // empty on purpose
    } else {
      returnedList = returnedList.concat(this.departments.filter(department => department.toLowerCase().includes(filterValue)));
    }

    return returnedList;
  }

  public showResults(): void {
    this.isResultShown = true;
    this.filteredOptions.subscribe(data => this.displayedList = data);

  }

}


export class Department {
  name: string;
}
