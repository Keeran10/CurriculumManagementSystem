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

import { Component, OnInit } from '@angular/core';
import { ApiService } from '../backend-api.service';
import { User } from '../models/user';
import * as _ from "lodash"

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  users: User[] = [];
  originalUsers: User[] = [];
  changeList: User[] = [];
  userTypes = ['Professor', 'Department Curriculum Committee', 'Faculty Council', 'APC', 'Department Council', 'UGSC', 'Senate'];

  showPassword = false;

  constructor(private api: ApiService) { }

  ngOnInit() {
    this.api.getAllUsers().subscribe(data => {
      data.sort((a,b) => a.lastName > b.lastName ? 1: -1);
      data.sort((a,b) => a.userType > b.userType ? 1: -1);
      this.users = data;
      this.originalUsers = _.cloneDeep(data);
    });
  }

  public invertPasswordShown(id: number) {
    var element = <HTMLInputElement>document.getElementById(id.toString());
    if (element.type === "password") {
      element.type = "text";
    } else {
      element.type = "password";
    }
  } 

  public areEqualUsers(user1, user2){
    
    if(user1.id === user2.id &&
      user1.firstName === user2.firstName &&
      user1.lastName === user2.lastName &&
      user1.userType === user2.userType &&
      user1.email === user2.email &&
      user1.password === user2.password){
        return true;
      }
    return false;
  }

  public submitChanges(){
    for(let i=0; i<this.originalUsers.length; i++){
      if(!this.areEqualUsers(this.originalUsers[i], this.users[i])){
        this.changeList.push(this.users[i]);
      }
    }
    console.log(this.changeList);
    this.api.updateUsers(this.changeList).subscribe(data => {
      const res = JSON.stringify(data);
      if (res.includes('200')) {
        const success = document.getElementById('success');
        success.style.visibility = 'visible';
      } else if (res.includes('400')) {
        const fail = document.getElementById('fail');
        fail.style.visibility = 'visible';
      }
    });
  }
}
