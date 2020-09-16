package com.squad.notebook.producer;

import com.squad.notebook.model.NoteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Note Event Producer")
@RequiredArgsConstructor
public class NoteEventProducer {

    private final KafkaTemplate<String, NoteEvent> noteEventKafkaTemplate;
    @Value("${topic-note.name}")
    private String topicName;

    public void produceNoteEvent(String key , NoteEvent noteEvent) {
        this.noteEventKafkaTemplate.send(this.topicName , key , noteEvent);
        log.info(String.format("Producing Note Event -> %s" , noteEvent));
    }

}
