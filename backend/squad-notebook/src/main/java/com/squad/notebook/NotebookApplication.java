package com.squad.notebook;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class NotebookApplication {

    @Value("${topic-note.name}")
    private String topicNoteName;

    @Value("${topic-note.partitions-num}")
    private Integer partitionsNote;

    @Value("${topic-note.replication-factor}")
    private short replicationFactorNote;

    @Value("${topic-notebook.name}")
    private String topicNotebookName;

    @Value("${topic-notebook.partitions-num}")
    private Integer partitionsNotebook;

    @Value("${topic-notebook.replication-factor}")
    private short replicationFactorNotebook;

    public static void main(String[] args) {
        SpringApplication.run(NotebookApplication.class , args);
    }

    @Bean
    NewTopic noteTopic() {
        return TopicBuilder.name(topicNotebookName)
                .partitions(partitionsNotebook)
                .replicas(replicationFactorNotebook)
                .config(TopicConfig.RETENTION_MS_CONFIG , "-1")
                .compact()
                .build();
    }

    @Bean
    NewTopic notebookTopic() {
        return TopicBuilder.name(topicNoteName)
                .partitions(partitionsNote)
                .replicas(replicationFactorNote)
                .config(TopicConfig.RETENTION_MS_CONFIG , "-1")
                .compact()
                .build();
    }

}
