package com.squad.chat;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class ChatApplication {

    @Value("${topic-chat.name}")
    private String topicChatName;

    @Value("${topic-chat.partitions-num}")
    private Integer partitionsChat;

    @Value("${topic-chat.replication-factor}")
    private short replicationFactorChat;

    @Value("${topic-chatroom.name}")
    private String topicChatroomName;

    @Value("${topic-chatroom.partitions-num}")
    private Integer partitionsChatroom;

    @Value("${topic-chatroom.replication-factor}")
    private short replicationFactorChatroom;

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class , args);
    }

    @Bean
    NewTopic chatTopic() {
        return TopicBuilder.name(topicChatroomName)
                .partitions(partitionsChatroom)
                .replicas(replicationFactorChatroom)
                .config(TopicConfig.RETENTION_MS_CONFIG , "-1")
                .compact()
                .build();
    }

    @Bean
    NewTopic chatroomTopic() {
        return TopicBuilder.name(topicChatName)
                .partitions(partitionsChat)
                .replicas(replicationFactorChat)
                .config(TopicConfig.RETENTION_MS_CONFIG , "-1")
                .compact()
                .build();
    }
}
