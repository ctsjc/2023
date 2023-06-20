package com.example.msv0001.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceA {
    @Value("${external.portfolio}")
    String portfolio;

    public String makeACall(){
        RestTemplate restTemplate=new RestTemplate();
        String api="/hello";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(portfolio + api, String.class);
        return "From Portfolio: ["+ forEntity.getBody() +"]";
    }
}
