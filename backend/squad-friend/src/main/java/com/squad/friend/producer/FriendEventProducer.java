package com.squad.friend.producer;

import com.squad.friend.model.FriendEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Friend Event Producer Logger")
@RequiredArgsConstructor
public class FriendEventProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, FriendEvent> friendEventKafkaTemplate;

    public void producerFriendEvent(String key , FriendEvent friendEvent) {
        this.friendEventKafkaTemplate.send(this.topicName , key , friendEvent);
        log.info(String.format("Produce Friend Event -> %s",friendEvent));
    }
}
