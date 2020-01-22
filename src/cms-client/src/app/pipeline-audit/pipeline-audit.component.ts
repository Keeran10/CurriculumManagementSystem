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
import { PipelineRevisions } from '../models/pipeline-revisions';

@Component({
  selector: 'app-pipeline-audit',
  templateUrl: './pipeline-audit.component.html',
  styleUrls: ['./pipeline-audit.component.css']
})
export class PipelineAuditComponent implements OnInit {

  revisions = new Array<PipelineRevisions>();
  pipelineId: string;

  constructor(private cookieService: CookieService,
              private api: ApiService) { }

  ngOnInit() {
    this.pipelineId = this.cookieService.get('pipeline');
    console.log(this.pipelineId);
    this.api.getPipelineAudit(this.pipelineId).subscribe(
        data => { this.revisions = data;
    }
    );
    }
}
