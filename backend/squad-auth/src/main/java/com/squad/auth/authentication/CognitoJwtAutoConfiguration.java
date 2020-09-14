package com.squad.auth.authentication;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.squad.auth.security.filter.AwsCognitoIdTokenProcessor;
import com.squad.auth.security.filter.AwsCognitoJwtAuthenticationFilter;
import com.squad.auth.util.AWSConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@Import(AWSConfiguration.class)
@ConditionalOnClass({ AwsCognitoJwtAuthenticationFilter.class , AwsCognitoIdTokenProcessor.class })
public class CognitoJwtAutoConfiguration {

    private final AWSConfiguration jwtConfiguration;

    @Autowired
    public CognitoJwtAutoConfiguration(AWSConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CognitoJwtIdTokenCredentialsHolder awsCognitoCredentialsHolder() {
        return new CognitoJwtIdTokenCredentialsHolder();
    }

    @Bean
    public AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor() {
        return new AwsCognitoIdTokenProcessor();
    }

    @Bean
    public CognitoJwtAuthenticationProvider jwtAuthenticationProvider() {
        return new CognitoJwtAuthenticationProvider();
    }


    @Bean
    public AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter() {
        return new AwsCognitoJwtAuthenticationFilter(awsCognitoIdTokenProcessor());
    }

    @SuppressWarnings({ "rawtypes" , "unchecked" })
    @Bean
    public ConfigurableJWTProcessor configurableJWTProcessor() throws MalformedURLException {
        ResourceRetriever resourceRetriever = new DefaultResourceRetriever(jwtConfiguration.getConnectionTimeout() , jwtConfiguration.getReadTimeout());
        //https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json.
        URL jwkSetURL = new URL(jwtConfiguration.getJwkUrl());
        JWKSource keySource = new RemoteJWKSet(jwkSetURL , resourceRetriever);
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
        JWSKeySelector keySelector = new JWSVerificationKeySelector(JWSAlgorithm.RS256 , keySource);
        jwtProcessor.setJWSKeySelector(keySelector);
        return jwtProcessor;
    }
}
