import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { PipelineTrackingComponent } from './pipeline-tracking.component';

describe('PipelineTrackingComponent', () => {
  let component: PipelineTrackingComponent;
  let fixture: ComponentFixture<PipelineTrackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PipelineTrackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PipelineTrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
