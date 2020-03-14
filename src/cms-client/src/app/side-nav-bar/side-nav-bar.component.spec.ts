import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SideNavBarComponent } from './side-nav-bar.component';
import { TopNavBarComponent } from '../top-nav-bar/top-nav-bar.component';
import {MatListModule} from '@angular/material/list';
import {ChildrenOutletContexts, RouterModule} from '@angular/router';
import {MatSidenavModule} from '@angular/material/sidenav';
import {FooterComponent} from '../footer/footer.component';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {CookieService} from 'ngx-cookie-service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterTestingModule} from '@angular/router/testing';

describe('SideNavBarComponent', () => {
  let component: SideNavBarComponent;
  let fixture: ComponentFixture<SideNavBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SideNavBarComponent,
        TopNavBarComponent,
        FooterComponent ],
      imports: [ MatListModule,
        RouterModule,
        MatSidenavModule,
        MatIconModule,
        MatToolbarModule,
        BrowserAnimationsModule,
        RouterTestingModule],
      providers: [
        CookieService,
        ChildrenOutletContexts,
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SideNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
