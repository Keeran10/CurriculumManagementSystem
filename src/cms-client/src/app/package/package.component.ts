import { ApiService } from '../backend-api.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {

  constructor(private cookieService: CookieService, private api: ApiService) { }

  ngOnInit() {
    //get packages
  }

  public packageSelect(){
    this.cookieService.set('package', '')
  }

}
