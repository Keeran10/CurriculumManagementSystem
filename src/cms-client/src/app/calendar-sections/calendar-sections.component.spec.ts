import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarSectionsComponent } from './calendar-sections.component';

describe('CalendarSectionsComponent', () => {
  let component: CalendarSectionsComponent;
  let fixture: ComponentFixture<CalendarSectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalendarSectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarSectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
