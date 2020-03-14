import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { MediaMatcher} from '@angular/cdk/layout';
import { SideNavService } from '../side-nav.service';

@Component({
  selector: 'app-top-nav-bar',
  templateUrl: './top-nav-bar.component.html',
  styleUrls: ['./top-nav-bar.component.css']
})
export class TopNavBarComponent implements OnInit {

  toggleActive = false;

  mobileQuery: MediaQueryList;
  // tslint:disable-next-line:variable-name
  private _mobileQueryListener: () => void;

  constructor(private cookieService: CookieService,
              private sidenav: SideNavService,
              changeDetectorRef: ChangeDetectorRef,
              media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  userName = 'User';
  isAdmin = false;
  isLogged = false;

  toggleSidenav() {
    this.toggleActive = !this.toggleActive;
    this.sidenav.toggle();
  }

  ngOnInit() {
    this.userName = this.cookieService.get('userName');
    const userType = this.cookieService.get('userType');
    if (userType === 'admin') {
      this.isAdmin = true;
    }
    this.isLoggedIn();
  }

  isLoggedIn() {
    if (this.cookieService.get('logged') !== '0') {
      this.isLogged = true;
    }
  }

  logOut() {
    this.cookieService.deleteAll();
    this.cookieService.set('logged', '0');
    this.isLoggedIn();
  }
}
