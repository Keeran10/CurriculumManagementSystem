import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { PipelineTrackingComponent } from './pipeline-tracking.component';
import { ApiService } from '../backend-api.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PipelineTrackingComponent', () => {
  let component: PipelineTrackingComponent;
  let fixture: ComponentFixture<PipelineTrackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [ PipelineTrackingComponent ],
      providers: [
        ApiService
      ]
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
