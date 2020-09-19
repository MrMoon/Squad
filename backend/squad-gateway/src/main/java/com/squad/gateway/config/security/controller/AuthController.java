package com.squad.gateway.config.security.controller;

import com.squad.gateway.config.security.model.CognitoJWT;
import com.squad.gateway.config.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication and Authorization for the Squad API
 * where it's responsible for communicating between AWS Cognito and Squad
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${endpoints.authorize}")
    private String authorizeUrl;  //AWS Cognito authorization URL
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint for the user to connect the the AWS Cognito Authentication
     *
     * @return HttpStatus based on the Redirection to AWS Cognito
     */
    @GetMapping("/authentication")
    public ResponseEntity<?> auth() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).header(HttpHeaders.LOCATION , authorizeUrl).build();
    }

    /**
     * Endpoint for the user to connect to the AWS Cognito Authorization
     * and get the user JWT properties based on the code that was returned from AWS Cognito
     *
     * @param code the oauth code for the user that was returned by AWS Cognito
     *
     * @return a CognitoJWT Object that has the JWT properties
     */
    @GetMapping("/authorization")
    public CognitoJWT token(@RequestParam("code") String code) {
        return authService.getToken(code);
    }

}
