package com.squad.chat.service;

import com.squad.chat.model.Chatroom;

import java.util.List;
import java.util.Optional;

public interface ChatroomService {

    Optional<Chatroom> getChatroomById(String roomId);
    List<Chatroom> getUserChatrooms(String userId);
    Chatroom create(String userID , Chatroom chatroom);
    Optional<Chatroom> addUser(String userId , String roomId);

}
