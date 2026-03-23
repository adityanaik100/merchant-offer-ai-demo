package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.dto.InvestigationContext;
import com.ai.offers.agent.demo.merchantofferai.model.Campaign;
import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestigationContextService {

    private final OfferService offerService;
    private final CampaignService campaignService;
    private final OfferLogService offerLogService;
    private final IncidentService incidentService;
    private final RuleService ruleService;

    public InvestigationContextService(OfferService offerService,
                                       CampaignService campaignService,
                                       OfferLogService offerLogService,
                                       IncidentService incidentService,
                                       RuleService ruleService) {
        this.offerService = offerService;
        this.campaignService = campaignService;
        this.offerLogService = offerLogService;
        this.incidentService = incidentService;
        this.ruleService = ruleService;
    }

    public InvestigationContext buildContext(String offerId) {
        Optional<Offer> offerOptional = offerService.getOfferById(offerId);

        if (offerOptional.isEmpty()) {
            return new InvestigationContext(
                    UUID.randomUUID().toString(),
                    null,
                    null,
                    List.of(),
                    List.of(),
                    List.of(),
                    List.of("Offer record not found for the supplied ID")
            );
        }

        Offer offer = offerOptional.get();

        Campaign campaign = null;
        if (offer.getCampaignId() != null) {
            campaign = campaignService.getCampaignById(offer.getCampaignId()).orElse(null);
        }

        List<OfferLog> logs = offerLogService.getLogsByOfferId(offerId);
        List<Incident> incidents = incidentService.getIncidentsByOfferId(offerId);

        String appliesTo = offer.getOfferType() != null ? offer.getOfferType().name() : "ALL_OFFERS";
        List<RuleDefinition> rules = ruleService.getRulesForOfferType(appliesTo);

        List<String> guidance = buildOperationalGuidance(offer, incidents, logs);

        return new InvestigationContext(
                UUID.randomUUID().toString(),
                offer,
                campaign,
                logs,
                incidents,
                rules,
                guidance
        );
    }

    private List<String> buildOperationalGuidance(Offer offer, List<Incident> incidents, List<OfferLog> logs) {
        List<String> guidance = new ArrayList<>();

        guidance.add("Use only the provided offer, incident, log, campaign, and rules data to determine the most likely issue.");
        guidance.add("Do not assume facts that are not present in the evidence.");
        guidance.add("If evidence is insufficient, explicitly say so and recommend improved observability.");
        guidance.add("Prefer precise operational RCA over generic summaries.");
        guidance.add("Consider whether the issue may require workaround, permanent defect, or duplicate incident linking.");

        if (offer != null && offer.isUserSubmissionBlocked()) {
            guidance.add("User submission appears blocked, so assess whether priority should be increased.");
        }

        if (incidents.size() > 1) {
            guidance.add("Multiple incidents exist for this offer. Check for duplicate or related issue patterns.");
        }

        boolean limitedLogs = logs.stream()
                .anyMatch(log -> log.getMessage() != null &&
                        log.getMessage().toUpperCase().contains("INSUFFICIENT LOGS"));

        if (limitedLogs) {
            guidance.add("Logs indicate limited observability. Consider recommending logging improvements and lower environment reproduction.");
        }

        return guidance;
    }

    public InvestigationContext buildContextFromIncident(String incidentId) {
        Optional<com.ai.offers.agent.demo.merchantofferai.model.Incident> incidentOptional =
                incidentService.getIncidentById(incidentId);

        if (incidentOptional.isEmpty()) {
            return new InvestigationContext(
                    java.util.UUID.randomUUID().toString(),
                    null,
                    null,
                    java.util.List.of(),
                    java.util.List.of(),
                    java.util.List.of(),
                    java.util.List.of("Incident record not found for the supplied incident ID")
            );
        }

        com.ai.offers.agent.demo.merchantofferai.model.Incident incident = incidentOptional.get();
        return buildContext(incident.getOfferId());
    }
}