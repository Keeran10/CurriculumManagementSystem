import { MediaMatcher } from '@angular/cdk/layout';
import { Component, OnInit, OnDestroy, ChangeDetectorRef, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material';
import {SideNavService} from '../side-nav.service';

@Component({
  selector: 'app-side-nav-bar',
  templateUrl: './side-nav-bar.component.html',
  styleUrls: ['./side-nav-bar.component.css']
})
export class SideNavBarComponent implements OnInit, OnDestroy {

  sidenavList1: Array<{ name: string, link: string }> = [
    { name: 'Search', link: 'search' },
    { name: 'Calendar', link: 'calendar' },
    { name: 'My account', link: 'account' },
    { name: 'Settings', link: 'settings' },
    { name: 'My messages', link: 'messages' },
    { name: 'Notifications', link: 'notifications' },
    { name: 'Logout', link: '' }];

  mobileQuery: MediaQueryList;
  fillerNav = Array.from(this.sidenavList1);
  fillerContent = Array.from({ length: 6 }, () =>
    ` --text content-- `);
  // tslint:disable-next-line:variable-name
  private _mobileQueryListener: () => void;

  // @ts-ignore
  @ViewChild('snav') public sidenav: MatSidenav;
  constructor(changeDetectorRef: ChangeDetectorRef,
              media: MediaMatcher,
              private sidenavService: SideNavService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit() {
    this.sidenavService.setSidenav(this.sidenav);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }
}
