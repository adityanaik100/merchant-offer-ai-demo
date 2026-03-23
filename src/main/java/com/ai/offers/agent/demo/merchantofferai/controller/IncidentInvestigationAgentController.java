package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.agent.OpenAiInvestigationService;
import com.ai.offers.agent.demo.merchantofferai.dto.InvestigationContext;
import com.ai.offers.agent.demo.merchantofferai.dto.LlmInvestigationResponse;
import com.ai.offers.agent.demo.merchantofferai.service.InvestigationContextService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent/incident-investigation")
public class IncidentInvestigationAgentController {

    private final InvestigationContextService investigationContextService;
    private final OpenAiInvestigationService openAiInvestigationService;

    public IncidentInvestigationAgentController(InvestigationContextService investigationContextService,
                                                OpenAiInvestigationService openAiInvestigationService) {
        this.investigationContextService = investigationContextService;
        this.openAiInvestigationService = openAiInvestigationService;
    }

    @GetMapping("/offer/{offerId}")
    public LlmInvestigationResponse investigateOffer(@PathVariable String offerId) {
        InvestigationContext context = investigationContextService.buildContext(offerId);
        return openAiInvestigationService.analyze(context);
    }
}