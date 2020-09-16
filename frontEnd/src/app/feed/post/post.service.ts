import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor() { }
  getPostsByGroupID(ID){
  
    if(ID==1){     
      console.log("test");
  return this.getPosts();
}

return ;
    
  
  }
  getPosts(){
    return [{

      postID:"2",
      userID:"1",
      time:"Just Now",
      name:"ACM PSUT",
      imgSrc:"../assets/psut.png",
      text:`
Hello ACMers!
It’s time for the biggest programming contest PSUT has to offer, the annual Coding marathon, in its first virtual iteration!
The Marathon will take place on Saturday, August 22nd, at 11 AM, and will be 5 hours long. Whether you want to participate on your own, or with a team of up to 3 PSUT students, make sure you register before 11:59 PM on Wednesday, August 19th using this link:

https://forms.gle/hvGvbk75McLe7fk66

Kindly note that this contest is exclusive to PSUT students.
Show us the best you’ve got! 🔥🔥🔥
      `,
      likeCount:13,
      commentCount:4,
      comments:[],
      commentShow:false,
      isLiked:false,
      POST_TYPE:"Normal",
      newComment:""

    },{
      postID:"1",
      userID:"1",
      time:"",
      name:"Mohammad Rimawi",
      imgSrc:"../assets/user.jpg",
      text:
`Hello! 👋🏻
After our last successful Mafia session and as we discussed back then, we'll be hosting another game today from 6 to 8 PM.
Feel free to join us whenever you want within this time at the following link:

https://psut-edu-jo.zoom.us/j/96164934232

Please have your university account ready in case you have any trouble joining the room.
Let‘s enjoy the lockdown together!  😊
`,
      likeCount:0,
      commentCount:4,
      comments:[{
        name:"Mohammad Rimawi",
        imgSrc:"../assets/user.jpg",
        text:"Lorem Lorem"
      }],
      commentShow:false,
      isLiked:false,
      POST_TYPE:"Normal",
      newComment:""
  }
    ,{

      postID:"2",
      userID:"1",
      time:"",
      name:"Mohammad Rimawi",
      imgSrc:"../assets/user.jpg",
      text:"Lorem, ipsum dolor sit amet consectetur adipisicing elit. Non perferendis dignissimos alias, rem tempora sequi eligendi quo! Asperiores similique architecto, dolores, quisquam eveniet quasi corporis harum numquam, tempore reiciendis delectus.Lorem, ipsum dolor sit amet consectetur adipisicing elit. Non perferendis dignissimos alias, rem tempora sequi eligendi quo! Asperiores similique architecto, dolores, quisquam eveniet quasi corporis harum numquam, tempore reiciendis delectus.Lorem, ipsum dolor sit amet consectetur adipisicing elit. Non perferendis dignissimos alias, rem tempora sequi eligendi quo! Asperiores similique architecto, dolores, quisquam eveniet quasi corporis harum numquam, tempore reiciendis delectus.",
      likeCount:13,
      commentCount:4,
      comments:[],
      commentShow:false,
      isLiked:false,
      POST_TYPE:"Normal",
      newComment:""

    }
  ];
  }
}
