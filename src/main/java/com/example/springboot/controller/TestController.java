package com.example.springboot.controller;

import com.example.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return testService.test();
    }
}
