import { ApiService } from '../backend-api.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Package } from '../model/package';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  packages: Package[];

  constructor(private cookieService: CookieService, private api: ApiService) { }

  ngOnInit() {
    // let departmentId = this.cookieService.get('department'); //replace 4 with department id
    this.api.getAllPackages('4').subscribe(data => {
      console.log(data);
      this.packages = data;
    });
  }

  public packageSelect(packageId, requestId){
    this.cookieService.set('package', packageId);
    this.cookieService.set('request', requestId);
    if(requestId != 0){
      let request = this.packages.find(p => p.id === packageId).requests.find(r => r.id === requestId);
      this.cookieService.set('originalCourse', request.originalId.toString());
      this.cookieService.set('editedCourse', request.targetId.toString());
    }
  }

  public createNewPackage(){
    this.api.getPackage('0', '4').subscribe(data => this.packages.push(data));
  }

  public generatePdf(packageId){
    this.api.generatePdf(packageId).subscribe(data => {
      let file = new Blob([data], {type: 'application/pdf'});
      var fileURL = URL.createObjectURL(file);
      console.log(file);
      console.log(fileURL);
      window.open(fileURL);
    });
  }
}
