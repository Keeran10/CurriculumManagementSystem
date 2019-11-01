import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ApprovalPipelineComponent } from './approval-pipeline.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatListModule } from '@angular/material/list';

describe('ApprovalPipelineComponent', () => {
  let component: ApprovalPipelineComponent;
  let fixture: ComponentFixture<ApprovalPipelineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatCheckboxModule,
        MatListModule
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
