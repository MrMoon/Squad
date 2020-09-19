package com.squad.gateway.config.security.service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.squad.gateway.config.kafka.AuthUserProducer;
import com.squad.gateway.config.security.model.CognitoJWT;
import com.squad.gateway.config.security.utils.JwtBase64;
import com.squad.gateway.model.User;
import lombok.RequiredArgsConstructor;
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

@Component
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${endpoints.token}")
    private String tokenUrl;

    @Value("${cognito.client}")
    private String clientId;

    @Value("${cognito.secret}")
    private String clientSecret;

    @Value("${cognito.callback}")
    private String callbackUrl;

    private final AuthUserProducer authUserProducer;

    public User getUser() throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTClaimsSet details = (JWTClaimsSet) authentication.getDetails();
        User user = new User();
        user.setUserId(details.getStringClaim("sub"));
        user.setAuthTime(details.getClaim("auth_time").toString());
        user.setIssuedAt(details.getClaim("iat").toString());
        user.setExpiresIn(details.getClaim("exp").toString());
        user.setGivenName(details.getStringClaim("given_name"));
        user.setFamilyName(details.getStringClaim("family_name"));
        user.setBirthdate(details.getStringClaim("birthdate"));
        user.setGender(details.getStringClaim("gender"));
        user.setUsername(details.getStringClaim("cognito:username"));
        user.setEmail(details.getStringClaim("email"));
        this.authUserProducer.produceUser(user.getUserId() , user);
        return user;
    }

    public CognitoJWT getToken(String code) {
        val headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization" , "Basic " + JwtBase64.toBase64(this.clientId + ':' + this.clientSecret));
        headers.add("Content-Type" , "application/x-www-form-urlencoded");
        return new RestTemplate().postForObject("" + this.tokenUrl + "?grant_type=authorization_code&client_id=" + this.clientId + "&code=" + code + "&redirect_uri=" + this.callbackUrl
                , new HttpEntity<>(null , headers)
                , CognitoJWT.class);
    }
}
