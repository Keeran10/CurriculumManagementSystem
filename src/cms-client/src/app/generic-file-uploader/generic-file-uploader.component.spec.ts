import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericFileUploaderComponent } from './generic-file-uploader.component';

describe('GenericFileUploaderComponent', () => {
  let component: GenericFileUploaderComponent;
  let fixture: ComponentFixture<GenericFileUploaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenericFileUploaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenericFileUploaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
