package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.dto.InvestigationContext;
import com.ai.offers.agent.demo.merchantofferai.service.InvestigationContextService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investigation-context")
public class InvestigationContextController {

    private final InvestigationContextService investigationContextService;

    public InvestigationContextController(InvestigationContextService investigationContextService) {
        this.investigationContextService = investigationContextService;
    }

    @GetMapping("/offer/{offerId}")
    public InvestigationContext getInvestigationContext(@PathVariable String offerId) {
        return investigationContextService.buildContext(offerId);
    }
}