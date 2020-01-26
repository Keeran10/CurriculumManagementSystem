import { Component, OnInit } from '@angular/core';
import {Section} from '../models/section';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../backend-api.service';
import {CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-calendar-section',
  templateUrl: './calendar-section.component.html',
  styleUrls: ['./calendar-section.component.css']
})
export class CalendarSectionComponent implements OnInit {

  id: string;
  sectionOriginal: Section = new Section();
  sectionEditable: Section = new Section();

  isDeleteVisible = true;

  constructor(private route: ActivatedRoute, private api: ApiService,
              private cookieService: CookieService,
              private router: Router) { }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const requestId = this.cookieService.get('request');
    const userId = this.cookieService.get('user');
    if (this.id === '0') {
      this.sectionEditable = new Section();
      this.sectionOriginal = Object.assign({}, this.sectionEditable);
      this.sectionOriginal.sectionId = null;
      this.sectionOriginal.firstCore = null;
      this.sectionOriginal.secondCore = null;
      this.isDeleteVisible = false;
    } else if (requestId === '0') {
      this.api.getSection(this.id).subscribe(data => {
        this.sectionOriginal = data;
        this.sectionEditable = Object.assign({}, data);
      }); } else {
      const originalId = this.cookieService.get('originalSection');
      const editedId = this.cookieService.get('editedSection');
      this.api.getSection(originalId).subscribe(data => {
        this.sectionOriginal = data;
      });
      this.api.getSection(editedId).subscribe(data => {
        this.sectionEditable = data;
        this.sectionEditable.id = Number(originalId);
      });
    }
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

  public submitForm() {
    this.api.submitCalendarSectionForm(this.sectionEditable)
      .subscribe(() => this.router.navigate(['/package']));
  }
}
