package com.demo.service.impl;

import com.demo.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String sayHi(String name) {
        return name + ", Hi";
    }
}
