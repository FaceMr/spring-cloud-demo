package com.demo.h5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
public class DemoConsumerH5Application {
    public static void main(String[] args) {
        SpringApplication.run(DemoConsumerH5Application.class, args);
    }
}
