import { Component, OnInit } from '@angular/core';
import { ChatService } from './chat.service';
export var msgs=[{
  isYou:false,
  senderID:1,
  senderImg:"../assets/laith.jpg",
  message:
`Hello!
`,
  time:""
  },{
    isYou:false,
    senderID:1,
    senderImg:"../assets/laith.jpg",
    message:
`Can anyone share the OOP notes with me?
`,
    time:""
    }
  ];

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  messages;
  newMessage;
  constructor(msgService:ChatService) {
    this.messages=msgs;
  }

    sendMessage(text){
      console.log(text);
     msgs.push({ "isYou":true,"senderID":1, "senderImg":"../assets/user.jpg","message":text,"time":""});
      this.messages=msgs;
    }
  ngOnInit(): void {
  }

}
