package com.demo.h5ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    private static String API_URL = "http://DEMO-PROVIDER-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiFallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000") },
            threadPoolProperties = { @HystrixProperty(name = "coreSize",value = "1"), @HystrixProperty(name="maxQueueSize",value = "20") } )
    @GetMapping("/api/hi/{name}")
    public String hi(@PathVariable String name){
        ResponseEntity<String> responseEntity =this.restTemplate.getForEntity(
                API_URL + "/service/hi/{1}",String.class,name );
        System.out.println(responseEntity.getHeaders().get("X-Application-Context"));
        return responseEntity.getBody();
    }


    /**
     * 该方法与hi方法具有相同的参数和返回值类型。
     * @return
     */
    public String hiFallback(String name){
        return "fallback:" + name;
    }

}
