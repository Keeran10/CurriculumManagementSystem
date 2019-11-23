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
import { ApiService } from '../backend-api.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
    selector: 'app-approval-pipeline',
    templateUrl: './approval-pipeline.component.html',
    styleUrls: ['./approval-pipeline.component.css']
  })

export class ApprovalPipelineComponent {

    constructor(private api: ApiService, private router: Router, 
        private cookieService: CookieService) {
    }

    public packageId = 1;
    // if user selects to create a custom pipeline, store here
    public customPipeline = [];
    // predefined pipeline order
    public predefinedPipeline = ['Department Curriculum Committee',
                                    'Department Council',
                                    'Associate Dean Academic Programs Under Graduate Studies Committee',
                                    'Faculty Council',
                                    'APC',
                                    'Senate'];
    ngOnInit() {
        this.packageId = Number(this.cookieService.get('package'));
    }
    public custom(opt) {
        let i;
        if (opt.length === 0) {
            alert('Cannot submit blank pipeline');
        } else {
            console.log(opt);
            for (i of opt) {
                this.customPipeline.push(i.value);
            }
            this.api.savePipeline(JSON.stringify(this.customPipeline), this.packageId)
      .subscribe(data => this.router.navigate(['package']) );
        }
    }
    public predefined() {
        console.log('User selected predefined pipeline');
        this.api.savePipeline(JSON.stringify(this.predefinedPipeline), this.packageId)
      .subscribe(data => this.router.navigate(['package']));
    }

}
