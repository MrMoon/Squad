package com.squad.chat.producer;

import com.squad.chat.model.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Chat Event Producer")
@RequiredArgsConstructor
public class MessageEventProducer {

    @Value("${topic-chat.name}")
    private String topicName;

    private final KafkaTemplate<String, MessageEvent> messageEventKafkaTemplate;

    public void produceMessageEvent(String key , MessageEvent messageEvent) {
        this.messageEventKafkaTemplate.send(this.topicName , key , messageEvent);
    }

}
