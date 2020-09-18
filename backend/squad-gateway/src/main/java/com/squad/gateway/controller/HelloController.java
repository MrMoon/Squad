package com.squad.gateway.controller;

import com.squad.gateway.config.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/api/hello")
@RequiredArgsConstructor
public class HelloController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() throws ParseException {
        return ResponseEntity.ok(this.getAuthService().getUser().toString());
    }

    public AuthService getAuthService() {
        return authService;
    }
}