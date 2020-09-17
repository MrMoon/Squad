package com.squad.gateway;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.squad.gateway.config.jwt.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@RequiredArgsConstructor
public class GatewayApplication {

    private final JwtConfiguration jwtConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class , args);
    }

    @Bean
    public ConfigurableJWTProcessor<?> configurableJWTProcessor() throws MalformedURLException {
        ConfigurableJWTProcessor<?> jwtProcessor = new DefaultJWTProcessor<>();
        jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(JWSAlgorithm.RS256 , new RemoteJWKSet<>(new URL(jwtConfiguration.getJwkUrl())
                , new DefaultResourceRetriever(jwtConfiguration.getConnectionTimeout() , jwtConfiguration.getReadTimeout()))));
        return jwtProcessor;
    }

}
