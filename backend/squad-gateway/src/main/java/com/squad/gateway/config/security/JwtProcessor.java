package com.squad.gateway.config.security;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class JwtProcessor {

    @Value("${cognito.keys}")
    private String keySource;

    @Bean
    public ConfigurableJWTProcessor<SecurityContext> contextConfigurableJWTProcessor() throws MalformedURLException {
        ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
        jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(JWSAlgorithm.RS256 , new RemoteJWKSet<>(new URL(this.keySource) , new DefaultResourceRetriever(5000 , 5000))));
        return jwtProcessor;
    }

}
