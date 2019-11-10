import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericFileUploaderComponent } from './generic-file-uploader.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('GenericFileUploaderComponent', () => {
  let component: GenericFileUploaderComponent;
  let fixture: ComponentFixture<GenericFileUploaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenericFileUploaderComponent ],
      schemas: [NO_ERRORS_SCHEMA],
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
