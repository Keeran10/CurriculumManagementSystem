import { Component, OnInit } from '@angular/core';
import { ApiService } from './backend-api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'cms-client';
  featureFlagTest = 'test';

  constructor(private apiService: ApiService) {}

  ngOnInit() {
  }

}
