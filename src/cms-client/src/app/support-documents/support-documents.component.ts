// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

import { ApiService } from '../backend-api.service';
import { Component, Input } from '@angular/core';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { getDocument } from 'pdfjs-dist';
import { SupportingDocument } from '../models/supporting-document';

@Component({
  selector: 'app-support-documents',
  templateUrl: './support-documents.component.html',
  styleUrls: ['./support-documents.component.css']
})

export class SupportDocumentComponent {

  public files: NgxFileDropEntry[] = [];
  public documents: File[] = [];
  public fileObjectArray: File[];
  public descriptions = new Map<string, string>();
  public pdfSrc;
  supportingDocs = new Array<SupportingDocument>();
  @Input() packageId: any;
  @Input() courseId: any;
  @Input() target_type: any;

  constructor(private api: ApiService) { }

  ngOnInit() {
    var id = 0;
    var type = "";
    console.log("target_type: " + this.target_type);
    if (this.target_type == 2) {
      id = this.courseId;
      type = "course";
    }
    else if (this.target_type == 1) {
      id = this.packageId;
      type = "dossier";
    }
    else {
      return;
    }
    this.api.getSupportingDocuments(id, type).subscribe(
      data => {
        this.supportingDocs = data;
        console.log(this.supportingDocs);
      }
    );
  }

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

  storeDescription(newValue, fileName) {
    this.descriptions.set(fileName, newValue);
    console.log(this.descriptions);
  }

  public showPDF(file_id: any) {
    this.api.getSupportingDocumentPdf(file_id).subscribe(
      data => {
        const file = new Blob([data], { type: 'application/pdf' });
        const fileURL = URL.createObjectURL(file);
        window.location.assign(fileURL);
      }
    )
  }

}
