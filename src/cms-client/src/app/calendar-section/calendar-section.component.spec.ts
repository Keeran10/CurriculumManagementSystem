import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarSectionComponent } from './calendar-section.component';

describe('CalendarSectionComponent', () => {
  let component: CalendarSectionComponent;
  let fixture: ComponentFixture<CalendarSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalendarSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
