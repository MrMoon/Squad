package com.squad.gateway.config.kafka;

import com.squad.gateway.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommonsLog(topic = "Auth User Producer")
public class AuthUserProducer {

    private final KafkaTemplate<String, User> userKafkaTemplate;
    @Value("${topic.name}")
    private String topicName;

    public void produceUser(String key , User user) {
        this.userKafkaTemplate.send(this.topicName , key , user);
    }

}
