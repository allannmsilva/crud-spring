package com.allan.videolocadora.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HWController {

    @GetMapping
    public String helloWorld() {
        return "Hello, Buddy! HAY?";
    }
}
