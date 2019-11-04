import { Component } from '@angular/core';

@Component({
    selector: 'app-approval-pipeline',
    templateUrl: './approval-pipeline.component.html',
    styleUrls: ['./approval-pipeline.component.css']
  })

export class ApprovalPipelineComponent {

    // if user selects to create a custom pipeline, store here
    public customPipeline = [];
    // predefined pipeline order
    public predefinedPipeline = ['Department Curriculum Committee',
                                    'Department Council',
                                    'Associate Dean Academic Programs Under Graduate Studies Committee',
                                    'Faculty Council',
                                    'APC',
                                    'Senate'];
    public custom(opt: string[]) {
        let i;
        if (opt.length === 0) {
            alert('Cannot submit blank pipeline');
        } else {
            for (i of opt) {
                this.customPipeline.push(i.value);
                console.log(i.value);
            }
        }
    }
    // placeholder logs user selected predefined pipeline until next page is created that will need this information
    public predefined() {
        console.log('User selected predefined pipeline');
    }

}
