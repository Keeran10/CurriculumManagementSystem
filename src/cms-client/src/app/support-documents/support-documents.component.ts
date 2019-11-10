import { Component } from '@angular/core';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { getDocument } from 'pdfjs-dist';

@Component({
  selector: 'app-support-documents',
  templateUrl: './support-documents.component.html',
  styleUrls: ['./support-documents.component.css']
})

export class SupportDocumentComponent {

  public files: NgxFileDropEntry[] = [];
  public documents: File[] = [];
  public fileObjectArray: File[];
  public pdfSrc;

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {

          // Here you can access the real file
          console.log(droppedFile.relativePath, file);
          this.documents.push(file);
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
