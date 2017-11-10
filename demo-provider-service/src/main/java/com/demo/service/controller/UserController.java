package com.demo.service.controller;

import com.demo.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/service/hi/{name}")
    public String getHi(@PathVariable String name, HttpServletRequest request){
        System.out.println("当前服务PORT：" + request.getServerPort());
        return userService.sayHi(name);
    }
}
