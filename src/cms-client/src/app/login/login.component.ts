// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/user';
import { CookieService } from 'ngx-cookie-service';
import { Department } from '../models/department';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private api: ApiService,
    private cookieService: CookieService,
    private router: Router
  ) {
  }

  isLoginError = false;
  email: string;
  login: string;
  password: string;
  user: User;
  department: Department;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.email = params.get('email'), this.password = params.get('password');
    });
    this.api.setCredentials(this.email, this.password).subscribe(data => {
      console.log(data);
    });
  }

  OnSubmit(username, password) {
    // user is captured and stored here
    this.api.setCredentials(username, password).subscribe((data => {
      this.user = data;
      this.cookieService.set('user', this.user.id.toString());
      this.cookieService.set('userName', this.user.firstName);
      this.cookieService.set('userType', this.user.userType);
      this.cookieService.set('department', this.user.department.id.toString());
      if (this.user.userType === 'Professor') {
        this.router.navigate(['/package']);
      } else { // if any approval body
        this.router.navigate(['homepage']);
      }
    }));
    console.log(this.user);
  }
}
