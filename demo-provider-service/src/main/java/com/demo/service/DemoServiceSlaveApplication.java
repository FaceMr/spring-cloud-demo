package com.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 激活Eureka中的DiscoveryClient实现(自动化配置,创建DiscoveryClient接口针对Eureka客户端的EurekaDiscoveryClient实例)
@EnableDiscoveryClient
@SpringBootApplication
public class DemoServiceSlaveApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoServiceSlaveApplication.class, args);
    }
}
