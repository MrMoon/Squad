package com.squad.auth.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:aws.properties")
@ConfigurationProperties
public class AWSConfiguration {

    private static final String COGNITO_IDENTITY_POOL_URL = "https://cognito-idp.us-west-2.amazonaws.com/%s";
    private static final String JSON_WEB_TOKEN_SET_URL_SUFFIX = "/.well-known/jwks.json";
    private String userNameField = "cognito:username", groupsField = "cognito:groups", httpHeader = "Authorization";

    private String clientId, poolId, endpoint, region, identityPoolId, developerGroup;
    private int connectionTimeout = 2000, readTimeout = 2000;

    public String getJwkUrl() {
        StringBuilder cognitoURL = new StringBuilder();
        cognitoURL.append(COGNITO_IDENTITY_POOL_URL);
        cognitoURL.append(JSON_WEB_TOKEN_SET_URL_SUFFIX);
        return String.format(cognitoURL.toString() , region , poolId);
    }

    public String getCognitoIdentityPoolUrl() {
        return String.format(COGNITO_IDENTITY_POOL_URL , region , poolId);
    }

}
