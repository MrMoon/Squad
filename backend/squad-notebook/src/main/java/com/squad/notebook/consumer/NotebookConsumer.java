package com.squad.notebook.consumer;

import com.squad.notebook.model.NotebookEvent;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Notebook Consumer Logger")
public class NotebookConsumer {

    @KafkaListener(
            topics = "#{'${topic-notebook.name}'}",
            groupId = "orgnizer-consumer"
    )
    public void consumerUser(NotebookEvent notebookEvent) {
        log.info(String.format("Consumed Notebook Event %s" , notebookEvent));
    }

}
