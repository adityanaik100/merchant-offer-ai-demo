package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.agent.OpenAiAutonomousOperationsService;
import com.ai.offers.agent.demo.merchantofferai.agent.OpenAiInvestigationService;
import com.ai.offers.agent.demo.merchantofferai.dto.AutonomousOperationsContext;
import com.ai.offers.agent.demo.merchantofferai.dto.AutonomousOperationsResponse;
import com.ai.offers.agent.demo.merchantofferai.dto.InvestigationContext;
import com.ai.offers.agent.demo.merchantofferai.dto.LlmInvestigationResponse;
import com.ai.offers.agent.demo.merchantofferai.dto.OperationalScenarioRequest;
import com.ai.offers.agent.demo.merchantofferai.dto.OperationalScenarioResponse;
import com.ai.offers.agent.demo.merchantofferai.service.AutonomousOperationsContextService;
import com.ai.offers.agent.demo.merchantofferai.service.IncidentService;
import com.ai.offers.agent.demo.merchantofferai.service.InvestigationContextService;
import com.ai.offers.agent.demo.merchantofferai.service.OperationalScenarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ai.offers.agent.demo.merchantofferai.agent.OpenAiProactiveDetectionService;
import com.ai.offers.agent.demo.merchantofferai.dto.ProactiveDetectionContext;
import com.ai.offers.agent.demo.merchantofferai.dto.ProactiveDetectionResponse;
import com.ai.offers.agent.demo.merchantofferai.service.ProactiveDetectionContextService;

@Controller
public class UiController {

    private final OperationalScenarioService operationalScenarioService;
    private final InvestigationContextService investigationContextService;
    private final OpenAiInvestigationService openAiInvestigationService;
    private final IncidentService incidentService;
    private final AutonomousOperationsContextService autonomousOperationsContextService;
    private final OpenAiAutonomousOperationsService openAiAutonomousOperationsService;
    private final ProactiveDetectionContextService proactiveDetectionContextService;
    private final OpenAiProactiveDetectionService openAiProactiveDetectionService;

    public UiController(OperationalScenarioService operationalScenarioService,
                        InvestigationContextService investigationContextService,
                        OpenAiInvestigationService openAiInvestigationService,
                        IncidentService incidentService,
                        AutonomousOperationsContextService autonomousOperationsContextService,
                        OpenAiAutonomousOperationsService openAiAutonomousOperationsService,
                        ProactiveDetectionContextService proactiveDetectionContextService,
                        OpenAiProactiveDetectionService openAiProactiveDetectionService) {
        this.operationalScenarioService = operationalScenarioService;
        this.investigationContextService = investigationContextService;
        this.openAiInvestigationService = openAiInvestigationService;
        this.incidentService = incidentService;
        this.autonomousOperationsContextService = autonomousOperationsContextService;
        this.openAiAutonomousOperationsService = openAiAutonomousOperationsService;
        this.proactiveDetectionContextService = proactiveDetectionContextService;
        this.openAiProactiveDetectionService = openAiProactiveDetectionService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/ui/offer-setup")
    public String offerSetupForm(Model model) {
        model.addAttribute("scenarioRequest", new OperationalScenarioRequest());
        return "offer-setup-form";
    }

    @PostMapping("/ui/offer-setup")
    public String createOfferSetup(@ModelAttribute("scenarioRequest") OperationalScenarioRequest request, Model model) {
        OperationalScenarioResponse response = operationalScenarioService.createScenario(request);
        model.addAttribute("scenarioResponse", response);
        return "offer-setup-success";
    }

    @GetMapping("/ui/investigate-incident")
    public String investigateIncidentForm(Model model) {
        model.addAttribute("openIncidents", incidentService.getOpenIncidents());
        return "investigate-incident-form";
    }

    @PostMapping("/ui/investigate-incident")
    public String investigateIncident(@RequestParam("incidentId") String incidentId, Model model) {
        InvestigationContext context = investigationContextService.buildContextFromIncident(incidentId);
        LlmInvestigationResponse result = openAiInvestigationService.analyze(context);

        model.addAttribute("incidentId", incidentId);
        model.addAttribute("context", context);
        model.addAttribute("result", result);

        return "investigation-result";
    }

    @GetMapping("/ui/incident-operations")
    public String incidentOperationsDashboard(Model model) {
        AutonomousOperationsContext context = autonomousOperationsContextService.buildContext();
        AutonomousOperationsResponse result = openAiAutonomousOperationsService.analyze(context);

        model.addAttribute("context", context);
        model.addAttribute("result", result);

        return "incident-operations-dashboard";
    }

    @GetMapping("/ui/proactive-detection")
    public String proactiveDetectionDashboard(Model model) {
        ProactiveDetectionContext context = proactiveDetectionContextService.buildContext();
        ProactiveDetectionResponse result = openAiProactiveDetectionService.analyze(context);

        model.addAttribute("context", context);
        model.addAttribute("result", result);

        return "proactive-detection-dashboard";
    }
}