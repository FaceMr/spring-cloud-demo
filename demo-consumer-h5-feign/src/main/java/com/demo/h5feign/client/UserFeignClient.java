package com.demo.h5feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "DEMO-PROVIDER-SERVICE")// demo-provider-service是客户端名称，用于创建Ribbon负载均衡器。
public interface UserFeignClient {
    // 这儿的value-"name"不能少，否则报错Caused by: java.lang.IllegalStateException: PathVariable annotation was empty on param 0.
    @RequestMapping("/service/hi/{name}")
    String hi(@PathVariable(value = "name") String name);
}
