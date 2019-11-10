import { Component, OnInit } from '@angular/core';
import { ApiService } from '../backend-api.service';

@Component({
    selector: 'app-approval-pipeline',
    templateUrl: './approval-pipeline.component.html',
    styleUrls: ['./approval-pipeline.component.css']
  })

export class ApprovalPipelineComponent {

    constructor(private api: ApiService) {
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
        //id = get package ID
    }
    public custom(opt: string[]) {
        let i;
        if (opt.length === 0) {
            alert('Cannot submit blank pipeline');
        } else {
            for (i of opt) {
                this.customPipeline.push(i.value);
            }
            this.api.savePipeline(JSON.stringify(this.customPipeline), this.packageId)
      .subscribe(data => { console.log(data); });
        }
    }
    public predefined() {
        console.log('User selected predefined pipeline');
        this.api.savePipeline(JSON.stringify(this.predefinedPipeline), this.packageId)
      .subscribe(data => { console.log(data); });
    }

}
