import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TopNavBarComponent } from './top-nav-bar.component';
import { CookieService } from 'ngx-cookie-service';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('TopNavBarComponent', () => {
  let component: TopNavBarComponent;
  let fixture: ComponentFixture<TopNavBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopNavBarComponent ],
      providers: [
        CookieService
      ],
      schemas: [NO_ERRORS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  describe('navbar tests', () => {
    function setup() {
      const cookieService = TestBed.get(CookieService);

      return { cookieService };

    }

    it('should create', () => {
      const {} = setup();
      expect(component).toBeTruthy();
    });

  });
});
