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

import { ApiService } from '../backend-api.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Package } from '../models/package';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  packages: Package[];
  isPdfAvailable = [];
  userName = 'User';

  constructor(private cookieService: CookieService, private api: ApiService) { }

  ngOnInit() {
    // let departmentId = this.cookieService.get('department'); //replace 4 with department id
    this.api.getAllPackages('4').subscribe(data => {
      console.log(data);
      this.packages = data;
      this.packages.forEach(() => this.isPdfAvailable.push(false));
    });
    this.userName = this.cookieService.get('userName');
  }

  public packageSelect(packageId, requestId) {
    this.cookieService.set('package', packageId);
    this.cookieService.set('request', requestId);
    if (requestId != 0) {
      let request = this.packages.find(p => p.id === packageId).requests.find(r => r.id === requestId);
      this.cookieService.set('originalCourse', request.originalId.toString());
      this.cookieService.set('editedCourse', request.targetId.toString());
    }
  }

  public createNewPackage() {
    this.api.getPackage('0', '4').subscribe(data => this.packages.push(data));
  }

  public generatePdf(packageId, index) {
    this.api.generatePdf(packageId).subscribe(data => this.isPdfAvailable[index] = data);
  }

  public viewPdf(packageId) {
    this.api.viewPdf(packageId).subscribe(data => {
      let file = new Blob([data], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL, '_blank');
      //window.location.assign(fileURL);
    });
  }
}
