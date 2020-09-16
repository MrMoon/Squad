import { AppComponent, left, right } from './../app.component';
import { Component, OnInit } from '@angular/core';
import { GroupService } from '../group/group.service';
// import {activeChatRoom, activeGroup ,activeOrganizer} from '../../global';
export var activeChatRoom=-1;
export var activeGroup=1;
export var darkMode=false;
export var activeOrganizer=-1;


@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  groups;
  
  post
l=left;
r=right;

  updateGroup(GID){
    activeGroup =GID.groupID;  
    console.log(activeGroup);      
  }
 
  swap(){
    let temp=this.r;
    this.r=this.l;
    this.l=temp;

    activeOrganizer=1;
  }

  swithDarkMode(){
    console.log("is dark", darkMode);

    if(darkMode) darkMode=false;
    else darkMode = true;
  }
  activateOrganizer(){
    activeOrganizer=1;     
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
