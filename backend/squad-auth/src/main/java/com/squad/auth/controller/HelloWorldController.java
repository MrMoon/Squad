package com.squad.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> sayHelloWorld() {
        return ResponseEntity.ok("Hello World");
    }
}