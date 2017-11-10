package com.demo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableEurekaServer
public class DemoEurekaSlaveServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaSlaveServerApplication.class, args);
    }
}
