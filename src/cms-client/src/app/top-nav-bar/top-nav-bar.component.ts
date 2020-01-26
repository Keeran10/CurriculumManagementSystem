import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-top-nav-bar',
  templateUrl: './top-nav-bar.component.html',
  styleUrls: ['./top-nav-bar.component.css']
})
export class TopNavBarComponent implements OnInit {

  userName = 'User';
  isAdmin = false;

  constructor(private cookieService: CookieService) { }

  ngOnInit() {
    this.userName = this.cookieService.get('userName');
    const userType = this.cookieService.get('userType');
    if (userType === 'admin') {
      this.isAdmin = true;
    }
  }

}
