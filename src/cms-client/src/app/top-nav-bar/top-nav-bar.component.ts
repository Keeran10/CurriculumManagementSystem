import {Component, OnInit, ViewChild} from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-top-nav-bar',
  templateUrl: './top-nav-bar.component.html',
  styleUrls: ['./top-nav-bar.component.css']
})
export class TopNavBarComponent implements OnInit {

  constructor(private cookieService: CookieService) {}

  userName = 'User';
  isAdmin = false;
  isLogged = false;

  // @ts-ignore
  @ViewChild('sidenav') sidenav: any;

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

  sidenavOpen() {
    this.sidenav.toggle();
  }
}
