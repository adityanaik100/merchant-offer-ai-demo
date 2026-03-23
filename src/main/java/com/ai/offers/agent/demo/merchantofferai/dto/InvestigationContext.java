package com.ai.offers.agent.demo.merchantofferai.dto;

import com.ai.offers.agent.demo.merchantofferai.model.Campaign;
import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;

import java.util.List;

public class InvestigationContext {

    private String investigationId;
    private Offer offer;
    private Campaign campaign;
    private List<OfferLog> logs;
    private List<Incident> incidents;
    private List<RuleDefinition> applicableRules;
    private List<String> operationalGuidance;

    public InvestigationContext() {
    }

    public InvestigationContext(String investigationId,
                                Offer offer,
                                Campaign campaign,
                                List<OfferLog> logs,
                                List<Incident> incidents,
                                List<RuleDefinition> applicableRules,
                                List<String> operationalGuidance) {
        this.investigationId = investigationId;
        this.offer = offer;
        this.campaign = campaign;
        this.logs = logs;
        this.incidents = incidents;
        this.applicableRules = applicableRules;
        this.operationalGuidance = operationalGuidance;
    }

    public String getInvestigationId() {
        return investigationId;
    }

    public void setInvestigationId(String investigationId) {
        this.investigationId = investigationId;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
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

    public List<RuleDefinition> getApplicableRules() {
        return applicableRules;
    }

    public void setApplicableRules(List<RuleDefinition> applicableRules) {
        this.applicableRules = applicableRules;
    }

    public List<String> getOperationalGuidance() {
        return operationalGuidance;
    }

    public void setOperationalGuidance(List<String> operationalGuidance) {
        this.operationalGuidance = operationalGuidance;
    }
}