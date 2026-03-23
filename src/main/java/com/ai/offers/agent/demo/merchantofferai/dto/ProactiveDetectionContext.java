package com.ai.offers.agent.demo.merchantofferai.dto;

import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;

import java.util.List;

public class ProactiveDetectionContext {

    private String analysisId;
    private List<Offer> offers;
    private List<OfferLog> logs;
    private List<Incident> incidents;
    private List<RuleDefinition> rules;
    private List<String> detectionObjectives;

    public ProactiveDetectionContext() {
    }

    public ProactiveDetectionContext(String analysisId,
                                     List<Offer> offers,
                                     List<OfferLog> logs,
                                     List<Incident> incidents,
                                     List<RuleDefinition> rules,
                                     List<String> detectionObjectives) {
        this.analysisId = analysisId;
        this.offers = offers;
        this.logs = logs;
        this.incidents = incidents;
        this.rules = rules;
        this.detectionObjectives = detectionObjectives;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<OfferLog> getLogs() {
        return logs;
    }

    public void setLogs(List<OfferLog> logs) {
        this.logs = logs;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public List<RuleDefinition> getRules() {
        return rules;
    }

    public void setRules(List<RuleDefinition> rules) {
        this.rules = rules;
    }

    public List<String> getDetectionObjectives() {
        return detectionObjectives;
    }

    public void setDetectionObjectives(List<String> detectionObjectives) {
        this.detectionObjectives = detectionObjectives;
    }
}