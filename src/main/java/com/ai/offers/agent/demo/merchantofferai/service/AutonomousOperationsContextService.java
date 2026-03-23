package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.dto.AutonomousOperationsContext;
import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AutonomousOperationsContextService {

    private final OfferService offerService;
    private final IncidentService incidentService;
    private final OfferLogService offerLogService;
    private final RuleService ruleService;

    public AutonomousOperationsContextService(OfferService offerService,
                                              IncidentService incidentService,
                                              OfferLogService offerLogService,
                                              RuleService ruleService) {
        this.offerService = offerService;
        this.incidentService = incidentService;
        this.offerLogService = offerLogService;
        this.ruleService = ruleService;
    }

    public AutonomousOperationsContext buildContext() {
        List<Offer> offers = offerService.getAllOffers();
        List<Incident> incidents = incidentService.getAllIncidents();
        List<OfferLog> logs = offerLogService.getAllLogs();
        List<RuleDefinition> rules = ruleService.getAllRules();

        List<String> objectives = new ArrayList<>();
        objectives.add("Identify incidents whose priority should be raised based on user impact and offer launch proximity.");
        objectives.add("Identify duplicate or closely related incidents that should be linked to one primary incident or defect.");
        objectives.add("Identify offers at operational risk due to blocked submission, downstream failures, targeting issues, or insufficient logs.");
        objectives.add("Recommend actions for support, SRE, engineering, and product operations.");
        objectives.add("Use only the provided evidence and rules. Do not invent operational facts.");

        return new AutonomousOperationsContext(
                UUID.randomUUID().toString(),
                offers,
                incidents,
                logs,
                rules,
                objectives
        );
    }
}