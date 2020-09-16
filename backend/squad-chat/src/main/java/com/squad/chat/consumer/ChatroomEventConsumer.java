package com.squad.chat.consumer;

import com.squad.chat.model.ChatroomEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Chatroom Event Consumer")
public class ChatroomEventConsumer {

    @KafkaListener(
            topics = "#{'${topic-chatroom.name}'}",
            groupId = "chatroom-consumer"
    )
    public void consumerChatroom(ChatroomEvent chatroomEvent) {
        log.info(String.format("Consumed Chatroom Event %s" , chatroomEvent));
    }

}
