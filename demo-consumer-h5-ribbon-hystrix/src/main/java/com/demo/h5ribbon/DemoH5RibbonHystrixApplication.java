package com.demo.h5ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@EnableCircuitBreaker //启动断路器支持
@EnableDiscoveryClient
@SpringBootApplication
public class DemoH5RibbonHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoH5RibbonHystrixApplication.class, args);
    }
}
