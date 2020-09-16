import { Component } from '@angular/core';
import {activeChatRoom,activeGroup,activeOrganizer} from './navigation/navigation.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  
  title = 'Squad';
  
 
  
  
  ngOnInit(): void {
    console.log(activeChatRoom);
    console.log(activeGroup);
    console.log(activeOrganizer);
  }
}


