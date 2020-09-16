package com.squad.chat.service;

import com.squad.chat.model.Message;
import com.squad.chat.model.MessageEvent;
import com.squad.chat.model.MessageEventType;
import com.squad.chat.producer.MessageEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageEventProducer messageEventProducer;


    @Override
    public List<Message> getUserMessages(String roomId , String userId) {
        return null;
    }

    @Override
    public Message create(String roomId , Message message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setRoomId(roomId);
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setEventId(UUID.randomUUID().toString());
        messageEvent.setMessage(message);
        messageEvent.setMessageEventType(MessageEventType.MESSAGE_SEND);
        this.messageEventProducer.produceMessageEvent(message.getMessageId() , messageEvent);
        return message;
    }
}
