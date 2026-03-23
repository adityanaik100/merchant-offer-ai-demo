package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.dto.OperationalScenarioRequest;
import com.ai.offers.agent.demo.merchantofferai.dto.OperationalScenarioResponse;
import com.ai.offers.agent.demo.merchantofferai.service.OperationalScenarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scenarios")
public class OperationalScenarioController {

    private final OperationalScenarioService operationalScenarioService;

    public OperationalScenarioController(OperationalScenarioService operationalScenarioService) {
        this.operationalScenarioService = operationalScenarioService;
    }

    @PostMapping
    public OperationalScenarioResponse createScenario(@RequestBody OperationalScenarioRequest request) {
        return operationalScenarioService.createScenario(request);
    }
}