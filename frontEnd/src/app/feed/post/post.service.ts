import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor() { }

  getPosts(){
    return [{
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
    },{

      postID:"2",
      userID:"1",
      time:"Just Now",
      name:"Mohammad Rimawi",
      imgSrc:"../assets/user.jpg",
      text:"Lorem, ipsum dolor sit amet consectetur adipisicing elit. Non perferendis dignissimos alias, rem tempora sequi eligendi quo! Asperiores similique architecto, dolores, quisquam eveniet quasi corporis harum numquam, tempore reiciendis delectus.Lorem, ipsum dolor sit amet consectetur adipisicing elit. Non perferendis dignissimos alias, rem tempora sequi eligendi quo! Asperiores similique architecto, dolores, quisquam eveniet quasi corporis harum numquam, tempore reiciendis delectus.Lorem, ipsum dolor sit amet consectetur adipisicing elit. Non perferendis dignissimos alias, rem tempora sequi eligendi quo! Asperiores similique architecto, dolores, quisquam eveniet quasi corporis harum numquam, tempore reiciendis delectus.",
      likeCount:13,
      commentCount:4,
      comments:[{
        name:"Mohammad Rimawi",
        imgSrc:"../assets/user.jpg",
        text:"Lorem Lorem"
      },{
        name:"sadas Rimawi",
        imgSrc:"../assets/user.jpg",
        text:"Lorem Lorem"
      },{
        name:"Mohammad asda",
        imgSrc:"../assets/user.jpg",
        text:"Lorem Loredasdadm"
      },{
        name:"Mohammasdsaad Rimawi",
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
