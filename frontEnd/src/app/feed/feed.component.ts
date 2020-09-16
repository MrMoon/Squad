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
    this.posts=this.postServ.getPostsByGroupID(activeGroup);
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
    
    this.posts.forEach(element => {
      temp.push(element);  
    });

    console.log(temp);
    this.posts=temp;

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
