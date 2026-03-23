package com.ai.offers.agent.demo.merchantofferai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Merchant Offer AI Demo is running";
    }
}