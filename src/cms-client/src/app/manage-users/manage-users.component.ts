import { Component, OnInit } from '@angular/core';
import { ApiService } from '../backend-api.service';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  constructor(private api: ApiService) { }

  ngOnInit() {
    this.api.getAllUsers().subscribe(data => console.log(data));
  }

}
