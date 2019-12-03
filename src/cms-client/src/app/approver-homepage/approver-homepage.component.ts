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
import { Router } from '@angular/router';

@Component({
    selector: 'app-approver-homepage',
    templateUrl: './approver-homepage.component.html',
    styleUrls: ['./approver-homepage.component.css']
  })

export class ApproverHomepageComponent implements OnInit {

    packages: Package[];
    userName = 'User';

    constructor(private cookieService: CookieService,
                private api: ApiService,
                private router: Router) { }

    ngOnInit() {
        // let departmentId = this.cookieService.get('department'); //replace 4 with department id
        this.api.getAllPackages('4').subscribe(data => {
            console.log(data);
            this.packages = data;
            });
        this.userName = this.cookieService.get('userName');
    }

    // generate PDF before viewing
    public generatePdf(packageId) {
        this.api.generatePdf(packageId).subscribe(data => console.log(data));
    }

    viewPDF(packageId: any) {
        this.generatePdf(packageId);
        this.api.viewPdf(packageId).subscribe(data => {
            const file = new Blob([data], { type: 'application/pdf' });
            const fileURL = URL.createObjectURL(file);
            window.location.assign(fileURL);
          });
    }

    // on edit
    public packageSelect(packageId) {
        this.cookieService.set('package', packageId);
        console.log(packageId);
        this.router.navigate(['package']);
    }

    // on decline
    public decline() {
        const rationaleInput = document.getElementById('rationale');
        rationaleInput.style.visibility = 'visible';
        const acceptButton = document.getElementById('acceptButton');
        acceptButton.style.visibility = 'hidden';
        const declineButton = document.getElementById('declineButton');
        declineButton.style.visibility = 'hidden';
        const submitRationaleButton = document.getElementById('submitRationale');
        submitRationaleButton.style.visibility = 'visible';
    }

    // on accept
    public accept(packageId) {
        let pipelineId;
        this.api.getPipeline(packageId).subscribe(data => pipelineId = data);
        this.api.setApprovalStatus(packageId, pipelineId, '', true);
        // push to next academic body in pipeline
    }

    // on decline --> pass in rationale
    public submitRationale(packageId, value) {
        let pipelineId;
        this.api.getPipeline(packageId).subscribe(data => pipelineId = data);
        this.api.setApprovalStatus(packageId, pipelineId, value, false);
    }
}
