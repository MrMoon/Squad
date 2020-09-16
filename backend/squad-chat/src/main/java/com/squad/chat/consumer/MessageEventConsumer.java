package com.squad.chat.consumer;

import com.squad.chat.model.ChatroomEvent;
import com.squad.chat.model.MessageEvent;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Message Consumer")
public class MessageEventConsumer {


    @KafkaListener(
            topics = "#{'${topic-chat.name}'}",
            groupId = "chat-consumer"
    )
    public void consumerChat(MessageEvent messageEvent) {
        log.info(String.format("Consumed Message Event %s" , messageEvent));
    }

}
