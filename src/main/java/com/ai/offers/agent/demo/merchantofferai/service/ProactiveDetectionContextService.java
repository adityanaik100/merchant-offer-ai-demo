package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.dto.ProactiveDetectionContext;
import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProactiveDetectionContextService {

    private final OfferService offerService;
    private final OfferLogService offerLogService;
    private final IncidentService incidentService;
    private final RuleService ruleService;

    public ProactiveDetectionContextService(OfferService offerService,
                                            OfferLogService offerLogService,
                                            IncidentService incidentService,
                                            RuleService ruleService) {
        this.offerService = offerService;
        this.offerLogService = offerLogService;
        this.incidentService = incidentService;
        this.ruleService = ruleService;
    }

    public ProactiveDetectionContext buildContext() {
        List<Offer> offers = offerService.getAllOffers();
        List<OfferLog> logs = offerLogService.getAllLogs();
        List<Incident> incidents = incidentService.getAllIncidents();
        List<RuleDefinition> rules = ruleService.getAllRules();

        List<String> objectives = new ArrayList<>();
        objectives.add("Detect hidden or emerging offer issues using offers, logs, and rules.");
        objectives.add("Identify offers that appear risky even if incident coverage is weak or missing.");
        objectives.add("Recommend incident creation or escalation where evidence supports it.");
        objectives.add("Identify observability gaps that prevent reliable diagnosis.");
        objectives.add("Use only the provided data and rules. Do not invent facts.");

        return new ProactiveDetectionContext(
                UUID.randomUUID().toString(),
                offers,
                logs,
                incidents,
                rules,
                objectives
        );
    }
}