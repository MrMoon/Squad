package com.squad.notebook.consumer;

import com.squad.notebook.model.NoteEvent;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Note Consumer Logger")
public class NoteConsumer {

    @KafkaListener(
            topics = "#{'${topic-note.name}'}",
            groupId = "orgnizer-consumer"
    )
    public void consumerUser(NoteEvent noteEvent) {
        log.info(String.format("Consumed Note Event %s" , noteEvent));
    }


}
