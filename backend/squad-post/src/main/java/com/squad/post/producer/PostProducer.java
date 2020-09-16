package com.squad.post.producer;

import com.squad.post.model.PostEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Post Producer Logger")
@RequiredArgsConstructor
public class PostProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String , PostEvent> postEventKafkaTemplate;

    public void producerPostEvent(String key , PostEvent postEvent) {
        this.postEventKafkaTemplate.send(this.topicName , key , postEvent);
        log.info(String.format("Produced Post Event -> %s", postEvent));
    }
}
