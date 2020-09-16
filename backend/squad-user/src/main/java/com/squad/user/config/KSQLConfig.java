package com.squad.user.config;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KSQLConfig {

    @Value("${ksql.host}")
    private String KSQLDB_SERVER_HOST;
    @Value("${ksql.port}")
    private Integer PORT;

    private static Client ksqlClient;

    @Bean
    public void configKSQLClient() {
        ClientOptions clientOptions = ClientOptions.create().setHost(this.KSQLDB_SERVER_HOST).setPort(this.PORT);
        ksqlClient = Client.create(clientOptions);
    }

    public static Client getKsqlClient() {
        return ksqlClient;
    }
}
