package com.squad.chat.service;

import com.squad.chat.model.Chatroom;
import com.squad.chat.model.ChatroomEvent;
import com.squad.chat.model.ChatroomEventType;
import com.squad.chat.producer.ChatroomEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService {

    private final ChatroomEventProducer chatroomEventProducer;


    @Override
    public Optional<Chatroom> getChatroomById(String roomId) {
        return Optional.empty();
    }

    @Override
    public List<Chatroom> getUserChatrooms(String userId) {
        return null;
    }

    @Override
    public Chatroom create(String userID , Chatroom chatroom) {
        chatroom.setUserId(userID);
        chatroom.setChatroomId(UUID.randomUUID().toString());
        ChatroomEvent chatroomEvent = new ChatroomEvent();
        chatroomEvent.setChatroom(chatroom);
        chatroomEvent.setChatroomEventType(ChatroomEventType.CHATROOM_CREATED);
        chatroomEvent.setEventId(UUID.randomUUID().toString());
        this.chatroomEventProducer.produceChatroomEvent(chatroom.getChatroomId() , chatroomEvent);
        return chatroom;
    }

    @Override
    public boolean addUser(String userId , String roomId) {
        Optional<Chatroom> chatroom = this.getChatroomById(roomId);
        if(chatroom.isEmpty()) return false;
        ChatroomEvent chatroomEvent = new ChatroomEvent();
        chatroom.get().setUserId(userId);
        chatroomEvent.setEventId(UUID.randomUUID().toString());
        chatroomEvent.setChatroomEventType(ChatroomEventType.CHATROOM_USER_ADDED);
        this.chatroomEventProducer.produceChatroomEvent(chatroom.get().getChatroomId() , chatroomEvent);
        return true;
    }
}
