package com.squad.gateway.config.security.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.squad.gateway.config.security.model.CognitoJWT;
import com.squad.gateway.config.security.model.User;
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

    public User getUser() throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTClaimsSet details = (JWTClaimsSet) authentication.getDetails();
        User user = new User();
        user.setUuid(details.getStringClaim("sub"));
        user.setAuthTime((Long) details.getClaim("auth_time"));
        user.setIssuedAt((Date) details.getClaim("iat"));
        user.setExpiresIn((Date) details.getClaim("exp"));
        user.setGiven_name(details.getStringClaim("given_name"));
        user.setFamily_name(details.getStringClaim("family_name"));
        user.setBirthdate(details.getStringClaim("birthdate"));
        user.setGender(details.getStringClaim("gender"));
        user.setCognitoUsername(details.getStringClaim("cognito:username"));
        user.setEmail(details.getStringClaim("email"));
        return user;
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
