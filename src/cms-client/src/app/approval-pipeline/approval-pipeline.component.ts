import { Component } from '@angular/core';

@Component({
    selector: 'app-approval-pipeline',
    templateUrl: './approval-pipeline.component.html',
    styleUrls: ['./approval-pipeline.component.css']
  })

export class ApprovalPipelineComponent {

    // if user selects to create a custom pipeline, store here
    public customPipeline = [];
    public custom(opt: string[]) {
        let i;
        if (opt.length === 0) {
            alert('Cannot submit blank pipeline');
        } else {
        for (i of opt) {
            this.customPipeline.push(i.value);
            console.log(i);
        }
    }
    }

}
