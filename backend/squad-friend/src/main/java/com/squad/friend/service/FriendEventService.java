package com.squad.friend.service;

import com.squad.friend.model.FriendEvent;

import java.util.List;

public interface FriendEventService {

    void save(FriendEvent friendEvent);
    List<String> getFollowers(String userId);

}
