import {
  PostService
} from './post/post.service';
import {
  FeedService
} from './feed.service';
import {
  Component,
  OnInit
} from '@angular/core';
import {
  element
} from 'protractor';

import {activeChatRoom,activeGroup,activeOrganizer} from '../navigation/navigation.component';

export var psts= [{

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

@Component({
  selector: 'feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  posts;
  user;
  Service: FeedService;
  postServ: PostService

  constructor(post: PostService) {
    this.postServ = new PostService;
    this.Service = new FeedService;
  }

  getPosts(){
    console.log("T");
    this.posts="";
    this.posts=psts;
  }

  like(elm) {
    console.log(elm.isLiked);
    
    if (elm.isLiked) elm.likeCount--;
    else elm.likeCount++;
    
    elm.isLiked = !elm.isLiked;

    this.Service.POST_LIKED(elm);
  }
  createPost(elm){
    let temp=[{
      "postID":"2",
      "userID":"1",
      "time":"",
      "name":"Mohammad Rimawi",
      "imgSrc":"../assets/user.jpg",
      "text":elm,
      "likeCount":0,
      "commentCount":0,
      "comments":[],
      "commentShow":false,
      "isLiked":false,
      "POST_TYPE":"Normal",
      "newComment":""
    }];
    
    psts.forEach(element => {
      temp.push(element);  
    });

    console.log(temp);
    psts=temp;
    this.posts=psts;
      this.Service.CREATE_POST(elm);
      
    }

    sendComment(elm){
    console.log(elm.newComment);
    elm.comments.append

elm.comments.push({ "name":"Mohammad Rimawi","imgSrc":"../assets/user.jpg","text":elm.newComment });
    this.Service.POST_COMMENT(elm)
  }


  test(){
    let t=[{
      postID:"1",
      userID:"1",
      time:"",
      name:"Mohammad Rimawi",
      imgSrc:"../assets/user.jpg",
      text:"lorem ipsum set amet",
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
  }];

  console.log(t);

  t[0]["test"]=true;
  console.log(t);


  }

  
  ngOnInit(): void {
    this.getPosts();
  }

}
