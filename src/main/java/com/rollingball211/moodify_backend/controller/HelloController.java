package com.rollingball211.moodify_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello moodify";
    }

    @GetMapping("/apiTest")
    public String test() {
        return "testing";
    }
}
