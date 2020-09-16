package com.squad.chat.controller;

import com.squad.chat.model.Message;
import com.squad.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{roomId}/{userId}")
    public ResponseEntity<List<Message>> getUserMessagesInRoom(@PathVariable("roomId") String roomId , @PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.messageService.getUserMessages(roomId , userId));
    }

    @PostMapping("/{roomId}/")
    public ResponseEntity<Message> sendMessage(@PathVariable("roomId") String roomId , @RequestBody Message message) {
        return ResponseEntity.ok(this.messageService.create(roomId , message));
    }

}
