package com.squid.follower.Service;

import com.squid.follower.Model.Follower;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FollowerService {

    public ArrayList<Follower> getAllFollowing(String id){
        return new ArrayList<Follower>();
    }
    public boolean followById(String userId,String friendId){
        return true;
    }
    public boolean unfollowById(String userId,String friendId){
        return true;
    }
}
