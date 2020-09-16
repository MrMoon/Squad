import { Component, OnInit } from '@angular/core';
import { ChatService } from './chat.service';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  messages;
newMessage;
  constructor(msgService:ChatService) {
    this.messages=msgService.getMessages(1);
  }

    sendMessage(text){
      console.log(text);
      this.messages.push({ "isYou":"true","senderID":"1", "senderImg":"../assets/user.jpg","message":text,"time":""});

    }
  ngOnInit(): void {
  }

}
