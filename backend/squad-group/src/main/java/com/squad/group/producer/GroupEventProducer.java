package com.squad.group.producer;

import com.squad.group.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupEventProducer {

    private final KafkaTemplate<String, Group> groupKafkaTemplate;
    @Value("topic.name")
    private String topic;

    public void produceGroupEvent(String key , Group group) {
        this.groupKafkaTemplate.send(topic , key , group);
    }

}
