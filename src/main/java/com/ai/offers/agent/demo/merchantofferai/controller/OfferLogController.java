package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.service.OfferLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class OfferLogController {

    private final OfferLogService offerLogService;

    public OfferLogController(OfferLogService offerLogService) {
        this.offerLogService = offerLogService;
    }

    @GetMapping
    public List<OfferLog> getAllLogs() {
        return offerLogService.getAllLogs();
    }

    @GetMapping("/offer/{offerId}")
    public List<OfferLog> getLogsByOfferId(@PathVariable String offerId) {
        return offerLogService.getLogsByOfferId(offerId);
    }
}