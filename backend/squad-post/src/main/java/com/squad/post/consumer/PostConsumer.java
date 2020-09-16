package com.squad.post.consumer;

import com.squad.post.model.PostEvent;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Post Consumer Logger")
public class PostConsumer {

    @KafkaListener(
            topics = "#{'${topic.name}'}",
            groupId = "post-consumer"
    )
    public void consumerUser(PostEvent postEvent) {
        log.info(String.format("Consumed Squid %s",postEvent));
    }

}
