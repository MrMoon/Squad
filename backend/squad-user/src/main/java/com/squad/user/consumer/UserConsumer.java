package com.squad.user.consumer;

import com.squad.user.model.User;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Squid Consumer Logger")
public class UserConsumer {

    @KafkaListener(
            topics = "#{'${topic.name}'}",
            groupId = "squid-consumer"
    )
    public void consumerUser(User user) {
        log.info(String.format("Consumed Squid %s",user));
    }



}
