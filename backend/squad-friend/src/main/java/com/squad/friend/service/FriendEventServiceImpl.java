package com.squad.friend.service;

import com.squad.friend.model.FriendEvent;
import com.squad.friend.producer.FriendEventProducer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FriendEventServiceImpl implements FriendEventService {

    private final FriendEventProducer friendEventProducer;

    @Override
    public void save(FriendEvent friendEvent) {
        friendEventProducer.producerFriendEvent(friendEvent.getFriendConnection().getUserId() , friendEvent);
    }

    @Override
    public List<String> getFollowers(String userId) {
        return null;
    }


}
