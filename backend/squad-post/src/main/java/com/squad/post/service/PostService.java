package com.squad.post.service;

import com.squad.post.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> getPostById(String postId);
    List<Post> getUserPosts(String userId);
    boolean likePost(String postId);
    boolean unlikePost(String postId);
    Post save(Post post , String postType);
    Post update(Post post , String postType);
    boolean delete(String postId);

}
