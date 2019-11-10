import { Component, Input, AfterViewInit } from '@angular/core';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { getDocument } from 'pdfjs-dist';

@Component({
  selector: 'app-generic-file-uploader',
  templateUrl: './generic-file-uploader.component.html',
  styleUrls: ['./generic-file-uploader.component.css']
})
export class GenericFileUploaderComponent implements AfterViewInit{

  @Input() documentName: string;

  public files: NgxFileDropEntry[] = [];
  public document: File;
  public fileObjectArray: File[];
  public pdfSrc;

  ngAfterViewInit(){
    let el1 = document.querySelectorAll('.ngx-file-drop__content');
    let el2 = document.querySelectorAll('.ngx-file-drop__drop-zone');
    el1.forEach((e) => {
      (<HTMLElement>e).style.height = '30px';
    });
    el2.forEach((e) => {
      (<HTMLElement>e).style.height = '30px';
    });
  }

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          this.document = file;
        });
      }
    }
  }

  public fileOver(event) {
    console.log(event);
  }

  public fileLeave(event) {
    console.log(event);
  }

}
