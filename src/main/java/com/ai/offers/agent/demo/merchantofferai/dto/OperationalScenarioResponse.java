package com.ai.offers.agent.demo.merchantofferai.dto;

import java.util.List;

public class OperationalScenarioResponse {

    private String offerId;
    private String campaignId;
    private String incidentId;
    private String message;
    private List<String> generatedArtifacts;

    public OperationalScenarioResponse() {
    }

    public OperationalScenarioResponse(String offerId, String campaignId, String incidentId,
                                       String message, List<String> generatedArtifacts) {
        this.offerId = offerId;
        this.campaignId = campaignId;
        this.incidentId = incidentId;
        this.message = message;
        this.generatedArtifacts = generatedArtifacts;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getGeneratedArtifacts() {
        return generatedArtifacts;
    }

    public void setGeneratedArtifacts(List<String> generatedArtifacts) {
        this.generatedArtifacts = generatedArtifacts;
    }
}