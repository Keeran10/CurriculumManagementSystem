import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImpactStatementComponent } from './impact-statement.component';

describe('ImpactStatementComponent', () => {
  let component: ImpactStatementComponent;
  let fixture: ComponentFixture<ImpactStatementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImpactStatementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImpactStatementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
