package com.example.msv0001.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("hello")
    public String sendMe(){
        System.out.println("Request Received...");
        return "Response From Portfolio";
    }
}
