package com.squad.chat.service;

import com.squad.chat.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getUserMessages(String roomId , String userId);
    Message create(String roomId , Message message);

}
