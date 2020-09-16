import {
  Injectable
} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor() {}

  getGroups(userID) {
    return [
      {
        groupID: 1,
        creatorID: 1,
        name: "Princess Sumaya Universty for Technology",
        shortName: "PSUT",
        chatRoomIDS: [{
          charRoomID: 1
        }, {
          charRoomID: 2
        }, {
          charRoomID: 3
        }],
        userIDs: [{
          userID: 1
        }, {
          userID: 2
        }, {
          userID: 3
        }],
        adminIDs: [{
          adminID: 1
        }, {
          adminID: 2
        }, {
          adminID: 3
        }],
        parentsId: [],
        subsID: [
          {
            subID:2
          },
          {
            subID:3
          }
        ],
        isAnonymous: false,
        isPublic: false,
        timeOfCreation: ""
      },{
        groupID: 2,
        creatorID: 1,
        name: "Object Orianted Programming",
        shortName: "OOP",
        chatRoomIDS: [{
          charRoomID: 1
        }, {
          charRoomID: 2
        }, {
          charRoomID: 3
        }],
        userIDs: [{
          userID: 1
        }, {
          userID: 2
        }, {
          userID: 3
        }],
        adminIDs: [{
          adminID: 1
        }, {
          adminID: 2
        }, {
          adminID: 3
        }],
        parentsID: [
          {
            parentsID: 1
          }
        ],
        subsID: [],
        isAnonymous: false,
        isPublic: false,
        timeOfCreation: ""
      },{
        groupID: 3,
        creatorID: 1,
        name: "Algorithm Analysis and Design",
        shortName: "Algo",
        chatRoomIDS: [{
          charRoomID: 1
        }, {
          charRoomID: 2
        }, {
          charRoomID: 3
        }],
        userIDs: [{
          userID: 1
        }, {
          userID: 2
        }, {
          userID: 3
        }],
        adminIDs: [{
          adminID: 1
        }, {
          adminID: 2
        }, {
          adminID: 3
        }],
        parentsID: [
          {
            parentsID: 1
          }
        ],
        subsID: [],
        isAnonymous: false,
        isPublic: false,
        timeOfCreation: ""
      }

    ];
  }
}
