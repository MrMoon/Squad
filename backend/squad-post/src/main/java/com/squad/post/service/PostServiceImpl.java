package com.squad.post.service;

import com.squad.post.model.Post;
import com.squad.post.model.PostEvent;
import com.squad.post.model.PostEventType;
import com.squad.post.model.PostType;
import com.squad.post.producer.PostProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostProducer postProducer;

    @Override
    public Optional<Post> getPostById(String postId) {
        return Optional.empty();
    }

    @Override
    public List<Post> getUserPosts(String userId) {
        return null;
    }

    @Override
    public boolean likePost(String postId) {
        return this.setupLike(postId , true);
    }

    @Override
    public boolean unlikePost(String postId) {
       return this.setupLike(postId , false);
    }

    @Override
    public Post save(Post post , String postType) {
        return this.produce(post , postType , PostEventType.POST_CREATED);
    }

    @Override
    public Post update(Post post , String postType) {
        return this.produce(post , postType , PostEventType.POST_UPDATED);
    }

    private Post produce(Post post , String postType , PostEventType postEventType) {
        postType = postType.toUpperCase();
        post.setPostType(PostType.valueOf(postType));
        post.setPostId(UUID.randomUUID().toString());
        PostEvent postEvent = new PostEvent();
        postEvent.setEventId(UUID.randomUUID().toString());
        postEvent.setPost(post);
        postEvent.setPostEventType(PostEventType.POST_UPDATED);
        this.postProducer.producerPostEvent(post.getPostId() , postEvent);
        return post;
    }

    private boolean setupLike(String postId , boolean isLike) {
        Optional<Post> post = this.getPostById(postId);
        if(post.isPresent()) {
            PostEvent postEvent = new PostEvent();
            postEvent.setPostEventType(isLike ? PostEventType.POST_LIKED : PostEventType.POST_UNLIKED);
            postEvent.setPost(post.get());
            postEvent.setEventId(UUID.randomUUID().toString());
            this.postProducer.producerPostEvent(postId , postEvent);
        }
        return false;
    }

    @Override
    public boolean delete(String postId) {
        Optional<Post> post = this.getPostById(postId);
        if(post.isPresent()) {
            PostEvent postEvent = new PostEvent();
            postEvent.setEventId(UUID.randomUUID().toString());
            postEvent.setPostEventType(PostEventType.POST_DELETED);
            postEvent.setPost(post.get());
            this.postProducer.producerPostEvent(postEvent.getEventId() , postEvent);
            return true;
        }
        return false;
    }
}
