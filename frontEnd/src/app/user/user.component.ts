import { UserService } from './user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  userServ:UserService;

  constructor() { 
    this.userServ=new UserService;  
  }

  getUserByID(id){
    return this.userServ.getUser(id);
  }


  
  ngOnInit(): void {
  }

}
