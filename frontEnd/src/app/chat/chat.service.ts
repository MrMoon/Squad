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
      senderImg:"../assets/user.jpg",
      message:"Lorem Ipsum",
      time:""
      },
      {
        isYou:true,
        senderID:1,
        senderImg:"../assets/Defult.svg",
        message:"Lorem Ipsum",
        time:""
        }];
  }
}
