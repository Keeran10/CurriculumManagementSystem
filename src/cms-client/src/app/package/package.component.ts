import { ApiService } from '../backend-api.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  packages;

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
    let request = this.packages.find(p => p.id === packageId).requests.find(r => r.id === requestId);
    this.cookieService.set('originalCourse', request.originalId);
    this.cookieService.set('editedCourse', request.targetId);
  }

  public createNewPackage(){
    this.api.getPackage('0', '4').subscribe(data => console.log(data));
  }

}
