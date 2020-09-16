package com.squad.friend.consumer;

import com.squad.friend.model.FriendEvent;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Friend Event Consumer Logger")
public class FriendEventConsumer {

    @KafkaListener(
            topics = "#{'${topic.name}'}",
            groupId = "follower-consumer"
    )
    public void consumerFriendEvent(FriendEvent friendEvent) {
        log.info(String.format("Consumed Friend Event %s",friendEvent));
    }

}
