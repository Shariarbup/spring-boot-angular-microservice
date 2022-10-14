package com.example.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class FallbackController {
    @GetMapping("/springAngularFallback")
    public String springAngularFallback(){
        return "Spring Angular service is down at this time";
    }
    @GetMapping("/contactServiceFallback")
    public String contactServiceFallback(){
        return "Contact Serviceis down at this time";
    }
}
