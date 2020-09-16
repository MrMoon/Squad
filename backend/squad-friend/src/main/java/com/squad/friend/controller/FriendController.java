package com.squad.friend.controller;

import com.squad.friend.model.Friend;
import com.squad.friend.model.FriendEvent;
import com.squad.friend.model.FriendEventType;
import com.squad.friend.service.FriendEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendEventService friendEventService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<String>> getFollowers(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(friendEventService.getFollowers(userId));
    }

    @PutMapping("/follow")
    public ResponseEntity<Boolean> followUser(@RequestBody Friend friend) {
        FriendEvent friendEvent = new FriendEvent();
        friendEvent.setEventId(UUID.randomUUID().toString());
        friendEvent.setFriendEventType(FriendEventType.FRIEND_ADDED);
        friendEvent.setFriendConnection(friend);
        friendEventService.save(friendEvent);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/unfollow")
    public ResponseEntity<Boolean> unfollowUser(@RequestBody Friend friend) {
        FriendEvent friendEvent = new FriendEvent();
        friendEvent.setEventId(UUID.randomUUID().toString());
        friendEvent.setFriendEventType(FriendEventType.FRIEND_REMOVE);
        friendEventService.save(friendEvent);
        return ResponseEntity.ok(true);
    }


}
