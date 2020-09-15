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

@Component({
  selector: 'feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  posts;
  user;
  Service: FeedService;
  constructor(post: PostService) {
    this.posts = post.getPosts();
    this.Service = new FeedService;
  }

  like(elm) {
    console.log(elm.isLiked);
    if (elm.isLiked) elm.likeCount--;
    else elm.likeCount++;
    elm.isLiked = !elm.isLiked;
    this.Service.POST_LIKED(elm);
  }

  sendComment(elm){
    console.log(elm.newComment);
    elm.comments.append

elm.comments.push({ "name":"Mohammad Rimawi","imgSrc":"../assets/user.jpg","text":elm.newComment });
    this.Service.POST_COMMENT(elm)
  }


  ngOnInit(): void {}

}
