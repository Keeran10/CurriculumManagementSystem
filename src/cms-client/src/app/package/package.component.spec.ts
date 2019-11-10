import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PackageComponent } from './package.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ApiService } from '../backend-api.service';
import { CookieService } from 'ngx-cookie-service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PackageComponent', () => {
  let component: PackageComponent;
  let fixture: ComponentFixture<PackageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ PackageComponent ],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
        ApiService,
        CookieService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PackageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
