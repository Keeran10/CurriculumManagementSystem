import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ApiService} from '../backend-api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private route: ActivatedRoute, private api: ApiService) { }

  email: string;
  password: string;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.email = params.get('email'), this.password = params.get('password');
    });
    this.api.getCredentials(this.email, this.password).subscribe(data => {
      console.log(data);
    });
  }

  login(): void {
  }

}
