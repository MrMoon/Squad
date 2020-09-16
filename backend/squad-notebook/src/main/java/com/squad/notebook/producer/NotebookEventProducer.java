package com.squad.notebook.producer;

import com.squad.notebook.model.NotebookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Notebook Event Producer")
@RequiredArgsConstructor
public class NotebookEventProducer {

    private final KafkaTemplate<String, NotebookEvent> notebookEventKafkaTemplate;
    @Value("${topic-notebook.name}")
    private String topicName;

    public void produceNotebookEvent(String key , NotebookEvent notebookEvent) {
        this.notebookEventKafkaTemplate.send(this.topicName , key , notebookEvent);
        log.info(String.format("Producing Notebook Event -> %s" , notebookEvent));
    }

}
