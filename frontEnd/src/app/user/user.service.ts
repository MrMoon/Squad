import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getUser(id){
    id=0;
    return[{
      ID:1,
      firstName:"Mohammad",
      lastName:"Rimawi",
      email:"mohRimawiz@gmail.com",
      birthday:"29/5/2000",
      img:"../assets/user.jpg"
    }];
  }
}
