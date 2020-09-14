package com.squid.follower.API;

import com.squid.follower.Model.Follower;
import com.squid.follower.Service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/follower")
public class FollowerController {

    private final FollowerService followerService;
    @Autowired
    public FollowerController(FollowerService followerService){
        this.followerService=followerService;
    }

    @GetMapping("/{userId}")
    public ArrayList<Follower> getAllFriends(@PathVariable String userId){
        return followerService.getAllFollowing(userId);
    }

    @PostMapping( "/{userId}/{friendId}")
    public boolean followSomeId(@PathVariable String userId,@PathVariable String friendId){
        return  followerService.followById(userId,friendId);
    }

    @PostMapping( "/{userId}/{friendId}")
    public boolean unfollowSomeId(@PathVariable String userId,@PathVariable String friendId) {
        return followerService.unfollowById(userId, friendId);
    }
}
