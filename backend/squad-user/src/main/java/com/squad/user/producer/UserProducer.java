package com.squad.user.producer;

import com.squad.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@CommonsLog(topic = "User Producer Logger")
@RequiredArgsConstructor
public class UserProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, User> kafkaTemplate;

    public void produceUser(String key , User user) {
        this.kafkaTemplate.send(this.topicName , key , user);
        log.info(String.format("Produced user -> %s", user));
    }
}
