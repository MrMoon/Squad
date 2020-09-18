package com.squad.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    public String email() {
        return "Hello ";
    }

}
