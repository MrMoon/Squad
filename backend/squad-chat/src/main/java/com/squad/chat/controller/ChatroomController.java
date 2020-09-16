package com.squad.chat.controller;

import com.squad.chat.model.Chatroom;
import com.squad.chat.service.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatroomController {

    private final ChatroomService chatroomService;

    @GetMapping("/{roomId}")
    public ResponseEntity<Chatroom> getChatroomById(@PathVariable("roomId") String roomId) {
        return this.chatroomService.getChatroomById(roomId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Chatroom>> getUserChatrooms(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.chatroomService.getUserChatrooms(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Chatroom> create(@PathVariable("userId") String userId , @RequestBody Chatroom chatroom) {
        return ResponseEntity.ok(this.chatroomService.create(userId , chatroom));
    }

    @PutMapping("/{roomId}/{userId}")
    public ResponseEntity<Chatroom> addUser(@PathVariable("roomId") String roomId , @PathVariable("userId") String userId ) {
        return this.chatroomService.addUser(userId , roomId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
