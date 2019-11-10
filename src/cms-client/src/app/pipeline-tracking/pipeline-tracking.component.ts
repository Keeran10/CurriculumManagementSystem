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
  public generatePDF() {
    this.api.generatePdf(this.id.toString()).subscribe(data => console.log(data));
  }
  public viewPdf() {
    this.generatePDF();
    this.api.viewPdf(this.id.toString()).subscribe(data => {
      const file = new Blob([data], {type: 'application/pdf'});
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL, '_blank');
      // for Mac: window.location.assign(fileURL); instead of .open
    });
  }
  public ngOnInit() {
    this.getPipelineID();
    this.getPackageID();
    this.getPipeline();
    this.getPackageLocation();
  }
}
