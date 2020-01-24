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
    userId = 0;
    pipelineId = '0';
    user_id = '0';
    departmentId = 0;
    userType = 'User';
    userMap = new Map();
    locks = new Array();
    packageUnderReview = '0';

    constructor(private cookieService: CookieService,
        private api: ApiService,
        private router: Router) {
    }

    ngOnInit() {
        this.userType = this.cookieService.get('userType');
        this.populateUserMap();
        this.packageUnderReview = this.cookieService.get('reviewingPackage');
        this.api.getPackagesToBeApproved(this.userMap.get(this.userType)).subscribe(data => {
            this.packages = data;
            if (data.length === 0) {
                document.getElementById('empty').style.visibility = 'display';
            }
            this.populateInitialLocks();
        });
        this.userName = this.cookieService.get('userName');
        this.userId = parseInt(this.cookieService.get('user'), 10);
        this.user_id = this.cookieService.get('user');
        //this.api.releaseReviewKey(1).subscribe(data => console.log(data));
    }

    // generate PDF before viewing
    public generatePdf(packageId) {
        this.api.generatePdf(packageId, this.user_id).subscribe(data => console.log(data));
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
        this.router.navigate(['/package']);
    }

    // on decline
    public decline(packageId, value) {
        this.api.getPipeline(packageId).subscribe(data => {
            const utf8decoder = new TextDecoder();
            this.pipelineId = utf8decoder.decode(data);
            this.api.setApprovalStatus(this.userId, packageId, this.pipelineId, value, false).subscribe(
                data => window.location.reload()
            );
        });
    }

    // on accept
    public accept(packageId, value) {
        let rationale = ' '
        if (value != '') {
            rationale = value;
        }
        this.api.getPipeline(packageId).subscribe(data => {
            const utf8decoder = new TextDecoder();
            this.pipelineId = utf8decoder.decode(data);
            this.api.setApprovalStatus(this.userId, packageId, this.pipelineId, rationale, true).subscribe(
                data => this.router.navigateByUrl('/pipeline')
            );
        });
    }

    public populateUserMap() {
        this.userMap.set('Department Curriculum Committee', 'Department Curriculum Committee');
        this.userMap.set('Faculty Council', 'Faculty Council');
        this.userMap.set('APC', 'APC');
        this.userMap.set('Department Council', 'Department Council');
        this.userMap.set('UGSC', 'Associate Dean Academic Programs Under Graduate Studies Committee');
        this.userMap.set('Senate', 'Senate');
      }

    public populateInitialLocks() {
        this.packages.forEach(p => {
            this.api.isMutexAvailable(p.id).subscribe(data => this.locks[p.id] = data );
        });
    }

    public reviewPackage(packageId: any) {
        this.packageUnderReview = packageId;
        this.cookieService.set('reviewingPackage', packageId);
        this.api.getReviewKey(packageId).subscribe(data => console.log('get review key for package ' + packageId + ' ' + data));
    }

    public releaseReviewMutex(packageId: any) {
        this.cookieService.set('reviewingPackage', '0');
        console.log(this.cookieService.get('reviewingPackage'));
        this.packageUnderReview = '0';
        this.api.releaseReviewKey(packageId).subscribe(data => console.log('release review key for package ' + packageId + ' ' + data));
    }
}
