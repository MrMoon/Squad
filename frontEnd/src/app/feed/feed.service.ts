import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  constructor() { }

  POST_LIKED(elm){
      console.log("PostLiked");
  }

  POST_COMMENT(elm){
    console.log("CommentPosted");

  }

  CREATE_POST(elm){
    console.log("PostCreated");

  }
}
