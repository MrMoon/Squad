package com.squad.post.controller;

import com.squad.post.model.Post;
import com.squad.post.model.PostEvent;
import com.squad.post.model.PostEventType;
import com.squad.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") String postId) {
        return this.postService.getPostById(postId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.postService.getUserPosts(userId));
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Boolean> likePost(@PathVariable("postId") String postId) {
        return ResponseEntity.ok(this.postService.likePost(postId));
    }

    @PutMapping("/unlike/{postId}")
    public ResponseEntity<Boolean> unlikePost(@PathVariable("postId") String postId) {
        return ResponseEntity.ok(this.postService.unlikePost(postId));
    }

    @PostMapping("/{postType}")
    public ResponseEntity<Post> createPost(@PathVariable("postType") String postType , @RequestBody Post post) {
        ;
        return ResponseEntity.ok(this.postService.save(post , postType));
    }

    @PutMapping("/{postType}")
    public ResponseEntity<Post> updatePost(@PathVariable("postType") String postType , @RequestBody @NotNull Post post) {
        PostEvent postEvent = new PostEvent();
        postEvent.setEventId(UUID.randomUUID().toString());
        postEvent.setPost(post);
        postEvent.setPostEventType(PostEventType.POST_UPDATED);
        return ResponseEntity.ok(this.postService.save(post , postType));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable("postId") String postId) {
        return ResponseEntity.ok(this.postService.delete(postId));
    }

}
