package com.squad.chat.producer;

import com.squad.chat.model.Chatroom;
import com.squad.chat.model.ChatroomEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Chatroom Event Producer")
@RequiredArgsConstructor
public class ChatroomEventProducer {

    @Value("${topic-chatroom.name}")
    private String topicName;

    private final KafkaTemplate<String , ChatroomEvent> chatroomKafkaTemplate;

    public void produceChatroomEvent(String key , ChatroomEvent chatroomEvent) {
        this.chatroomKafkaTemplate.send(this.topicName , key , chatroomEvent);
    }

}
