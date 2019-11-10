import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../backend-api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private api: ApiService,
              ) {
  }

  isLoginError = false;
  email: string;
  login: string;
  password: string;
  user: User[];

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.email = params.get('email'), this.password = params.get('password');
    });
    this.api.setCredentials(this.email, this.password).subscribe(data => {
      console.log(data);
    });
  }

  OnSubmit(username, password) {
    // user is captured and stored here
    this.api.setCredentials(username, password).subscribe((data => this.user = data));
    console.log(this.user);
  }
}
