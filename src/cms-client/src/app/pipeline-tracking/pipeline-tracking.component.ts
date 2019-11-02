import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pipeline-tracking',
  templateUrl: './pipeline-tracking.component.html',
  styleUrls: ['./pipeline-tracking.component.css']
})

export class PipelineTrackingComponent implements OnInit {

  public id = 0;
  public packageLocation = 0;
  public pipeline = ['Department Curriculum Committee',
                                    'Department Council',
                                    'Associate Dean Academic Programs Under Graduate Studies Committee',
                                    'Faculty Council',
                                    'APC',
                                    'Senate'];
  public getPackageID() {
    this.id = 3; // will be replaced with backend call
  }
  public getPipeline() {
    // placeholder for when the backend code is ready
  }
  public getPackageLocation() {
    // placeholder to get package location in approval pipeline
    // location = get from backend, for now hardcoded
    const location = 'Associate Dean Academic Programs Under Graduate Studies Committee';
    let i;
    for (i in this.pipeline) {
        if (this.pipeline[i] === location) {
            this.packageLocation = i;
        }
    }
  }
  public ngOnInit() {
    this.getPackageID();
    // this.getPipeline();
    this.getPackageLocation();
  }
}
