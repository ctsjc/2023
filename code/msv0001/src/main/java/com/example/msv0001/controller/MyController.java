package com.example.msv0001.controller;

import com.example.msv0001.service.ServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    ServiceA serviceA;
    @GetMapping("hello")
    public String sendMe(){
        return "Response From MSV1";
    }

    @GetMapping("iscall")
    public String sendMe1(){
        return serviceA.makeACall();
    }
}
