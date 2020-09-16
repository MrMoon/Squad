package com.squad.user;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@EnableEurekaClient
@SpringBootApplication
public class UserApplication {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.partitions-num}")
    private Integer partitions;

    @Value("${topic.replication-factor}")
    private short replicationFactor;

    @Bean
    NewTopic userTopic() {
        return TopicBuilder.name(topicName)
                .partitions((short) 3)
                .replicas((short) 3)
                .config(TopicConfig.RETENTION_MS_CONFIG, "-1")
                .compact()
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class , args);
    }

}
