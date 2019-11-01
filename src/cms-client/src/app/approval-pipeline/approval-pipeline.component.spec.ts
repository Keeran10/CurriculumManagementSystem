import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ApprovalPipelineComponent } from './approval-pipeline.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatListModule } from '@angular/material/list';
import { MatCardModule, MatCard } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

describe('ApprovalPipelineComponent', () => {
  let component: ApprovalPipelineComponent;
  let fixture: ComponentFixture<ApprovalPipelineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatCheckboxModule,
        MatListModule,
        MatCardModule,
        MatCard,
        MatButtonModule
      ],
      declarations: [ ApprovalPipelineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalPipelineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
