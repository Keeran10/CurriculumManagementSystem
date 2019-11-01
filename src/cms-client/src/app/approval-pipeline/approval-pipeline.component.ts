import { Component } from '@angular/core';

@Component({
    selector: 'app-approval-pipeline',
    templateUrl: './approval-pipeline.component.html',
    styleUrls: ['./approval-pipeline.component.css']
  })

export class ApprovalPipelineComponent {

    public customPipeline;
    public custom(opt: string[]) {
        this.customPipeline = opt;
        console.log('items' + opt[0]);
    }

}
