import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-impact-statement',
  templateUrl: './impact-statement.component.html',
  styleUrls: ['./impact-statement.component.css']
})
export class ImpactStatementComponent {

  animal: string;
  name: string;

  constructor(public dialog: MatDialog) {
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogImpactStatementComponent, {
      width: '900px',
      data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }
}

@Component({
    selector: 'app-dialog-impact-statement',
    templateUrl: 'dialog-impact-statement.html',
  })
  export class DialogImpactStatementComponent {

    constructor(
      public dialogRef: MatDialogRef<DialogImpactStatementComponent>,
      @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    }

    onNoClick(): void {
      this.dialogRef.close();
    }

  }
