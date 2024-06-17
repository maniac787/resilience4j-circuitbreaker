package com.techprimers.circuitbreaker.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RequestMapping("/activity")
@RestController
public class ActivityController {

    private RestTemplate restTemplate;

    private final String BORED_API = "https://www.boredapi.com/api/activity";

    public ActivityController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @CircuitBreaker(name = "randomActivity", fallbackMethod = "fallbackRandomActivity")
    public Map getRandomActivity() {
        String BORED_API = "https://randomuser.me/api/";
        Map forObject = restTemplate.getForObject(BORED_API, Map.class);
        Map body = forObject;
//        log.info("Activity received: " + body.toString());
        log.info("Activity received: info");
        log.warn("Activity received: warn");
        log.error("Activity received: error");


        return body;
    }


    public String fallbackRandomActivity(Throwable throwable) {
        return "Watch a video from TechPrimers";
    }

}

