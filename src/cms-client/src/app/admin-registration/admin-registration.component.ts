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

import { Component, OnInit, Inject } from '@angular/core';
import { ApiService } from '../backend-api.service';
import { FormBuilder } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
    selector: 'app-admin-registration',
    templateUrl: './admin-registration.component.html',
    styleUrls: ['./admin-registration.component.css']
  })

  export class RegistrationComponent implements OnInit {
    userTypes = ['Professor', 'Department Curriculum Committee', 'Faculty Council', 'APC', 'Department Council', 'UGSC', 'Senate'];
    departmentId = 0;
    constructor(private formBuilder: FormBuilder, private api: ApiService, private cookieService: CookieService
    ) {}

    ngOnInit() {
        this.departmentId = parseInt(this.cookieService.get('department'), 10);
    }

    OnSubmit(firstname: any, lastname: any, usertype: any, email: any, password: any) {
      this.api.registerUser(firstname, lastname, usertype, email, password, this.departmentId).subscribe(data => {
        console.log(data);
        const res = JSON.stringify(data);
        if (res.includes('200')) {
          const success = document.getElementById('success');
          success.style.visibility = 'visible';
        } else if (res.includes('400')) {
          const fail = document.getElementById('fail');
          fail.style.visibility = 'visible';
        }
      });
    }
}
