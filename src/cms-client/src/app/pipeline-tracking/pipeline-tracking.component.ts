import { Component, OnInit } from '@angular/core';
import { ApiService } from '../backend-api.service';

@Component({
  selector: 'app-pipeline-tracking',
  templateUrl: './pipeline-tracking.component.html',
  styleUrls: ['./pipeline-tracking.component.css']
})

export class PipelineTrackingComponent implements OnInit {

  constructor(private api: ApiService) {
  }

  public id = 0;
  public packageLocation = '';
  public pipelineId = 0;
  // public pipeline = ['Department Curriculum Committee',
  //                                   'Department Council',
  //                                   'Associate Dean Academic Programs Under Graduate Studies Committee',
  //                                   'Faculty Council',
  //                                   'APC',
  //                                   'Senate'];
  public pipeline = [];
  public getPipelineID() {
    this.pipelineId = 1; // will be replaced when connected to Packages
  }
  public getPackageID() {
    this.id = 1; // will be replaced when connected to Packages
  }
  public getPipeline() {
    this.api.getApprovalPipeline(this.pipelineId).subscribe(data => { this.pipeline = data;
    });
  }
  public getPackageLocation() {
    let location;
    this.api.getCurrentPosition(this.id.toString(), this.pipelineId.toString()).subscribe(
      data => { location = data;
      });
    let i;
    for (i in this.pipeline) {
        if (this.pipeline[i] === location) {
            this.packageLocation = i;
        }
    }
  }
  // public getPDF() {
  //   this.api.getPDF(this.id).subscribe(data => {
  //       const blob = new Blob([data], { type: 'application/pdf' });
  //       if (window.navigator && window.navigator.msSaveOrOpenBlob) {
  //         window.navigator.msSaveOrOpenBlob(blob);
  //       } else {
  //         const objectUrl = URL.createObjectURL(blob);
  //         window.open(objectUrl);
  //       }
  //    });
  // }
  public ngOnInit() {
    this.getPipelineID();
    this.getPackageID();
    this.getPipeline();
    this.getPackageLocation();
  }
}
