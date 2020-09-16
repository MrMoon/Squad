import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor() { }

  getMessages(roomID){
    roomID=0;

    return[{
      isYou:false,
      senderID:1,
      senderImg:"../assets/laith.jpg",
      message:
`Rimawi
`,
      time:""
      },{
        isYou:false,
        senderID:1,
        senderImg:"../assets/laith.jpg",
        message:
`Can you share the OOP documents with me?
`,
        time:""
        }
      ];
  }
}
