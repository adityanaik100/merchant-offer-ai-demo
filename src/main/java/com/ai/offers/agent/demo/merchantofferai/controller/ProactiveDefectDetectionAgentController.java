package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.agent.OpenAiProactiveDetectionService;
import com.ai.offers.agent.demo.merchantofferai.dto.ProactiveDetectionContext;
import com.ai.offers.agent.demo.merchantofferai.dto.ProactiveDetectionResponse;
import com.ai.offers.agent.demo.merchantofferai.service.ProactiveDetectionContextService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent/proactive-defect-detection")
public class ProactiveDefectDetectionAgentController {

    private final ProactiveDetectionContextService proactiveDetectionContextService;
    private final OpenAiProactiveDetectionService openAiProactiveDetectionService;

    public ProactiveDefectDetectionAgentController(ProactiveDetectionContextService proactiveDetectionContextService,
                                                   OpenAiProactiveDetectionService openAiProactiveDetectionService) {
        this.proactiveDetectionContextService = proactiveDetectionContextService;
        this.openAiProactiveDetectionService = openAiProactiveDetectionService;
    }

    @GetMapping("/context")
    public ProactiveDetectionContext getContext() {
        return proactiveDetectionContextService.buildContext();
    }

    @GetMapping("/analyze")
    public ProactiveDetectionResponse analyze() {
        ProactiveDetectionContext context = proactiveDetectionContextService.buildContext();
        return openAiProactiveDetectionService.analyze(context);
    }
}