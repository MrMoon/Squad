package com.squad.gateway.config.security.controller;

import com.squad.gateway.config.security.model.CognitoJWT;
import com.squad.gateway.config.security.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${endpoints.authorize}")
    private final String authorizeUrl;
    private final AuthService authService;

    public AuthController(AuthService authService) {
        super();
        this.authService = authService;
        this.authorizeUrl = "";
    }

    public AuthService getAuthService() {
        return this.authService;
    }

    @GetMapping
    public String auth() {
        return "auth";
    }

    @GetMapping("/login")
    public ResponseEntity<Object> login() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).header(HttpHeaders.LOCATION , authorizeUrl).build();
    }

    @GetMapping("/token")
    public CognitoJWT token(@RequestParam("code") String code) {
        return authService.getToken(code);
    }

}
