package com.squad.gateway.config.security.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.squad.gateway.config.security.model.CognitoJWT;
import com.squad.gateway.config.security.model.TokenClaims;
import com.squad.gateway.config.security.utils.StringManipulation;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Date;

@Component
@Service
public class AuthService {

    @Value("${endpoints.token}")
    private String tokenUrl;

    @Value("${cognito.client}")
    private String clientId;

    @Value("${cognito.secret}")
    private String clientSecret;

    @Value("${cognito.callback}")
    private String callbackUrl;

    public TokenClaims getClaims() throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTClaimsSet details = (JWTClaimsSet) authentication.getDetails();
        TokenClaims tokenClaims = new TokenClaims();
        tokenClaims.setUuid(details.getStringClaim("sub"));
        tokenClaims.setAuthTime((Long) details.getClaim("auth_time"));
        tokenClaims.setIssuedAt((Date) details.getClaim("iat"));
        tokenClaims.setExpiresIn((Date) details.getClaim("exp"));
        tokenClaims.setName(details.getStringClaim("name"));
        tokenClaims.setCognitoUsername(details.getStringClaim("cognito:username"));
        tokenClaims.setEmail(details.getStringClaim("email"));
        return tokenClaims;
    }

    public CognitoJWT getToken(String code) {
        RestTemplate client = new RestTemplate();

        val headers = new LinkedMultiValueMap<String, String>();
        String auth = StringManipulation.toBase64(this.clientId + ':' + this.clientSecret);
        headers.add("Authorization" , "Basic " + auth);
        headers.add("Content-Type" , "application/x-www-form-urlencoded");
        HttpEntity<?> req = new HttpEntity<>(null , headers);

        String url = "" + this.tokenUrl + "?grant_type=authorization_code&client_id=" + this.clientId + "&code=" + code + "&redirect_uri=" + this.callbackUrl;
        return client.postForObject(url , req , CognitoJWT.class);
    }
}
