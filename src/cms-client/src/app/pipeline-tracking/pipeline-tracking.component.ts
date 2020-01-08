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
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-pipeline-tracking',
  templateUrl: './pipeline-tracking.component.html',
  styleUrls: ['./pipeline-tracking.component.css']
})

export class PipelineTrackingComponent implements OnInit {

  constructor(private api: ApiService, private cookieService: CookieService) {
  }

  public id = 1;
  public packageLocation = '';
  public pipelineId = 1;
  public pipeline = [];
  public userId = '0';
  //public getPipelineID() {
  //this.pipelineId = 1; // will be replaced when connected to Packages
  //}

  public delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  public getPackageID() {
    this.id = Number(this.cookieService.get('package'));
    console.log('getPackageID: ' + this.id);
  }
  public getPipeline() {
    this.api.getApprovalPipeline(this.pipelineId).subscribe(data => {
      this.pipeline = data;
    });
  }
  public getPackageLocation() {
    this.api.getCurrentPosition(this.id.toString(), this.pipelineId.toString()).subscribe(
      data => {
        const utf8decoder = new TextDecoder();
        let i;
        for (i in this.pipeline) {
          if (this.pipeline[i] === utf8decoder.decode(data)) {
            this.packageLocation = i;
          }
        }
      });
  }
  public generatePDF() {
    this.api.generatePdf(this.id.toString(), this.userId).subscribe(data => console.log(data));
  }
  public viewPdf() {
    this.generatePDF();
    this.api.viewPdf(this.id.toString()).subscribe(data => {
      const file = new Blob([data], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL, '_blank');
      // for Mac: window.location.assign(fileURL); instead of .open
    });
  }

  public getNewPipelineId() {
    //await this.delay(5000);
    console.log('getNewPipelineId ' + this.id.toString());
    this.api.getPipeline(this.id).subscribe(data => this.pipelineId = data);
  }

  public ngOnInit() {
    //this.getPipelineID();
    this.getPackageID();
    this.getPipeline();
    this.getPackageLocation();
    this.getNewPipelineId();
    this.userId = this.cookieService.get('user');
  }
}
