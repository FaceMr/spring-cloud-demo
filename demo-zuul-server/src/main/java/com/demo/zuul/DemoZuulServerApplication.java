package com.demo.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class DemoZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoZuulServerApplication.class, args);
    }
}
