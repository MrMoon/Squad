import { Component } from '@angular/core';
import {activeChatRoom,activeGroup,activeOrganizer,darkMode} from './navigation/navigation.component';


export var left="feed";
export var right="chat";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  
  title = 'Squad';
  r=right;
  l=left;
  
  constructor(){
    
  }
  
  isDark(){
     console.log("is dark", darkMode);
    return darkMode};
  swap(){
    let temp=right;
    right=left;
    left=temp;

     temp=this.r;
    this.r=this.l;
    this.l=temp;
  }
  
  ngOnInit(): void {
    
  }
}


