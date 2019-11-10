import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ApiService} from '../backend-api.service';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs/operators";

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

  email = new FormControl('', [Validators.required, Validators.email]);
  login: string;
  password: string;
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  hide = true;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.login = params.get('email'), this.password = params.get('password');
    });
    this.api.setCredentials(this.login, this.password).subscribe(data => {
      console.log(data);
    });

    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }


  onSubmit(): void {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
  }
    this.loading = true;
}
}
