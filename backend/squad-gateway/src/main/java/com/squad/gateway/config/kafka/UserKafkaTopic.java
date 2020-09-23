package com.squad.gateway.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Hashtable;
import java.util.Map;

@Configuration
public class UserKafkaTopic {

    @Value("${topic.name}")
    private String topicName;
    @Value("${topic.partitions-num}")
    private short partitionsNumber;
    @Value("${topic.replication-factor}")
    private short replicationFactor;

    @Bean
    NewTopic createUserTopic() {
        Map<String, String> properties = new Hashtable<>();
        properties.put(TopicConfig.CLEANUP_POLICY_CONFIG , TopicConfig.CLEANUP_POLICY_COMPACT);
        properties.put(TopicConfig.RETENTION_MS_CONFIG , "-1");
        return TopicBuilder
                .name(this.topicName)
                .partitions(this.partitionsNumber)
                .replicas(this.replicationFactor)
                .compact()
                .configs(properties)
                .build();
    }

}
