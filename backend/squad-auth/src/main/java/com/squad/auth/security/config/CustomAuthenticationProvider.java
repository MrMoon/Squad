package com.squad.auth.security.config;

import com.squad.auth.model.AuthenticationRequest;
import com.squad.auth.security.model.SpringSecurityUser;
import com.squad.auth.service.CognitoAuthenticationService;
import com.squad.auth.util.AWSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CognitoAuthenticationService cognitoAuthenticationService;

    @Autowired
    public CustomAuthenticationProvider(CognitoAuthenticationService cognitoAuthenticationService) {
        this.cognitoAuthenticationService = cognitoAuthenticationService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Authentication authenticate(Authentication authentication) {
        AuthenticationRequest authenticationRequest = null;


        if (null != authentication) {
            authenticationRequest = new AuthenticationRequest();
            Map<String, String> credentials = (Map<String, String>) authentication.getCredentials();
            authenticationRequest.setNewPassword(credentials.get(AWSConstants.NEW_PASS_WORD_KEY));
            authenticationRequest.setPassword(credentials.get(AWSConstants.PASS_WORD_KEY));
            authenticationRequest.setUsername(authentication.getName());
            SpringSecurityUser springSecurityUser = cognitoAuthenticationService.authenticate(authenticationRequest);
            if (springSecurityUser != null) {
                Map<String, String> authenticatedCredentials = new HashMap<>();
                authenticatedCredentials.put(AWSConstants.ACCESS_TOKEN_KEY , springSecurityUser.getAccessToken());
                authenticatedCredentials.put(AWSConstants.EXPIRES_IN_KEY , springSecurityUser.getExpiresIn().toString());
                authenticatedCredentials.put(AWSConstants.ID_TOKEN_KEY , springSecurityUser.getIdToken());
                authenticatedCredentials.put(AWSConstants.PASS_WORD_KEY , springSecurityUser.getPassword());
                authenticatedCredentials.put(AWSConstants.REFRESH_TOKEN_KEY , springSecurityUser.getRefreshToken());
                authenticatedCredentials.put(AWSConstants.TOKEN_TYPE_KEY , springSecurityUser.getTokenType());
                return new UsernamePasswordAuthenticationToken(springSecurityUser.getUsername() , authenticatedCredentials , springSecurityUser.getAuthorities());
            } else return null;
        } else
            throw new UsernameNotFoundException(String.format("No appUser found with username '%s'." , ""));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
