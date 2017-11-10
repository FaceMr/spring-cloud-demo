package com.demo.h5feign.controller;

import com.demo.h5feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFeignController {
    @Autowired
    UserFeignClient userFeignClient;

    @GetMapping(value = "/api/hi/{name}")
    public String hi(@PathVariable  String name){
        return userFeignClient.hi(name);
    }

}
