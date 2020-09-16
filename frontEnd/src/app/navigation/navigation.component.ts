import { AppComponent } from './../app.component';
import { Component, OnInit } from '@angular/core';
import { GroupService } from '../group/group.service';
// import {activeChatRoom, activeGroup ,activeOrganizer} from '../../global';

export var activeChatRoom=-1;
export var activeGroup=1;
export var activeOrganizer=-1;


@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  groups;
  post


  updateGroup(GID){
    activeGroup =GID.groupID;  
    console.log(activeGroup);      
  }

  
  constructor(groupSer:GroupService) {
    this.groups=groupSer.getGroups(1);
  }
  

  ngOnInit(): void {
  }

  nav = false;


  expand(){
    this.nav=true;
  }
  shrink(){
    this.nav=false;
  }

  classApplied = false;

  toggleClass() {
    this.classApplied = !this.classApplied;
  }

}
