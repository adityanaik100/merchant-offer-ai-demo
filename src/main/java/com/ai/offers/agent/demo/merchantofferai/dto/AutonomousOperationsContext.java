package com.ai.offers.agent.demo.merchantofferai.dto;

import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;

import java.util.List;

public class AutonomousOperationsContext {

    private String analysisId;
    private List<Offer> offers;
    private List<Incident> incidents;
    private List<OfferLog> logs;
    private List<RuleDefinition> rules;
    private List<String> operationalObjectives;

    public AutonomousOperationsContext() {
    }

    public AutonomousOperationsContext(String analysisId,
                                       List<Offer> offers,
                                       List<Incident> incidents,
                                       List<OfferLog> logs,
                                       List<RuleDefinition> rules,
                                       List<String> operationalObjectives) {
        this.analysisId = analysisId;
        this.offers = offers;
        this.incidents = incidents;
        this.logs = logs;
        this.rules = rules;
        this.operationalObjectives = operationalObjectives;
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

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public List<OfferLog> getLogs() {
        return logs;
    }

    public void setLogs(List<OfferLog> logs) {
        this.logs = logs;
    }

    public List<RuleDefinition> getRules() {
        return rules;
    }

    public void setRules(List<RuleDefinition> rules) {
        this.rules = rules;
    }

    public List<String> getOperationalObjectives() {
        return operationalObjectives;
    }

    public void setOperationalObjectives(List<String> operationalObjectives) {
        this.operationalObjectives = operationalObjectives;
    }
}