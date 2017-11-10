package com.demo.h5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/hi/{name}")
    public String get(@PathVariable String name){
        return this.restTemplate.getForObject("http://127.0.0.1:9996/service/hi/{1}",String.class, name);
    }

}
