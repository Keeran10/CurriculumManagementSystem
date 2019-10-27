import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { SupportDocumentComponent } from './support-documents.component';
import { NgxFileDropModule } from 'ngx-file-drop';

describe('SupportDocumentComponent', () => {
  let component: SupportDocumentComponent;
  let fixture: ComponentFixture<SupportDocumentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        NgxFileDropModule
      ],
      declarations: [ SupportDocumentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupportDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
