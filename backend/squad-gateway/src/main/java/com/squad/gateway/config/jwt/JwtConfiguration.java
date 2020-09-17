package com.squad.gateway.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties(prefix = "com.squad.jwt.aws")
@Data
public class JwtConfiguration {

    private String userPoolId, identityPoolId, jwkUrl, region = "us-west-2", userNameField = "congnito:username", httpHeader = "Authorization";
    private int connectionTimeout = 2000, readTimeout = 2000;

    public String getJwkUrl() {
        return this.jwkUrl != null && !this.jwkUrl.isEmpty() ? this.jwkUrl : String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json" , this.region , this.userPoolId);
    }

    public String getCognitoIdentityPoolUrl() {
        return String.format("https://cognito-idp.%s.amazonaws.com/%s" , this.region , this.userPoolId);
    }


}
