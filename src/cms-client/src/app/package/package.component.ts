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
import { Component, OnInit, ViewChild } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { SupportDocumentComponent } from '../support-documents/support-documents.component';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  @ViewChild(SupportDocumentComponent, { static: false })
  supportDocumentComponent: SupportDocumentComponent;

  packages = new Array();
  isPdfAvailable = new Array();
  isSubmitted = new Array();
  userName = 'User';
  userId = '0';
  selectedFiles: FileList;
  currentFile: File;
  msg;
  files: File[] = [];
  step = 0;
  editingPackage = '0'; // if coming from pipeline to edit

  constructor(private cookieService: CookieService,
              private api: ApiService,
              private router: Router) {
               }

  ngOnInit() {
     // let departmentId = this.cookieService.get('department'); // replace 4 with department id
     this.api.getAllPackages('4').subscribe(data => {
      console.log(data);
      this.packages = data;
      this.packages.forEach(() => this.isPdfAvailable.push(false));
    });
     this.userName = this.cookieService.get('userName');
     this.userId = this.cookieService.get('user');
     this.editingPackage = this.cookieService.get('editingPackage');
     console.log(this.editingPackage);
  }

  public packageSelect(packageId, requestId, href) {
    this.cookieService.set('package', packageId);
    console.log(packageId);
    this.cookieService.set('request', requestId);
    if (requestId != 0) {
      const request = this.packages.find(p => p.id === packageId).requests.find(r => r.id === requestId);
      this.cookieService.set('originalCourse', request.originalId.toString());
      this.cookieService.set('editedCourse', request.targetId.toString());
    }
    this.router.navigate([href]);
  }

  public viewAllRevisions(packageId, href) {
    this.cookieService.set('package', packageId.toString());
    console.log('PackageComponent packageId: ' + packageId);
    this.router.navigate([href]);
  }

  public createNewPackage() {
    this.api.getPackage('0', '4').subscribe(data => this.packages.push(data));
  }

  public generatePdf(packageId, index) {
    this.api.generatePdf(packageId, this.userId).subscribe(data => this.isPdfAvailable[index] = data);
  }

  public viewPdf(packageId) {
    this.api.viewPdfFromPackagePage(packageId, this.userId).subscribe(data => {
      const file = new Blob([data], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(file);
      // window.open(fileURL, '_blank');
      window.location.assign(fileURL);
    });
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    console.log(this.selectedFiles);
    this.files.push(this.selectedFiles.item(0));
    console.log(this.files);
  }

  upload(packageId: any) {

    this.api.uploadFile(this.supportDocumentComponent.documents, packageId, this.userId).subscribe(response => {
      if (response instanceof HttpResponse) {
        this.supportDocumentComponent.documents = [];
        this.msg = response.body;
        console.log(response.body);
      }
    });
  }

  public releaseMutex(packageId: any) {
    this.cookieService.set('editingPackage', '0'); // release the package from editing
    console.log(this.cookieService.get('editingPackage'));
    this.editingPackage = '0'; // release the package from editing
    this.api.releaseEditKey(packageId).subscribe(data => console.log('Release edit key of package ' + packageId + ' ' + data));
  }
}
