package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.agent.OpenAiAutonomousOperationsService;
import com.ai.offers.agent.demo.merchantofferai.dto.AutonomousOperationsContext;
import com.ai.offers.agent.demo.merchantofferai.dto.AutonomousOperationsResponse;
import com.ai.offers.agent.demo.merchantofferai.service.AutonomousOperationsContextService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent/incident-prioritization")
public class IncidentPrioritizationAgentController {

    private final AutonomousOperationsContextService autonomousOperationsContextService;
    private final OpenAiAutonomousOperationsService openAiAutonomousOperationsService;

    public IncidentPrioritizationAgentController(
            AutonomousOperationsContextService autonomousOperationsContextService,
            OpenAiAutonomousOperationsService openAiAutonomousOperationsService) {
        this.autonomousOperationsContextService = autonomousOperationsContextService;
        this.openAiAutonomousOperationsService = openAiAutonomousOperationsService;
    }

    @GetMapping("/context")
    public AutonomousOperationsContext getContext() {
        return autonomousOperationsContextService.buildContext();
    }

    @GetMapping("/analyze")
    public AutonomousOperationsResponse analyze() {
        AutonomousOperationsContext context = autonomousOperationsContextService.buildContext();
        return openAiAutonomousOperationsService.analyze(context);
    }
}