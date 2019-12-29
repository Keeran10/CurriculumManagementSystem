import { Component, OnInit } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {ApiService} from '../backend-api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-top-nav-bar',
  templateUrl: './top-nav-bar.component.html',
  styleUrls: ['./top-nav-bar.component.css']
})
export class TopNavBarComponent implements OnInit {

  userName = 'User';

  constructor(private cookieService: CookieService,
              private api: ApiService,
              private router: Router) { }

  ngOnInit() {
  }

}
