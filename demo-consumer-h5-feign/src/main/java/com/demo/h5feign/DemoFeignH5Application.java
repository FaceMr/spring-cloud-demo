package com.demo.h5feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients //开启SpringCloud Feign的支持功能
@SpringBootApplication
public class DemoFeignH5Application {
    public static void main(String[] args){
        SpringApplication.run(DemoFeignH5Application.class, args);
    }
}
