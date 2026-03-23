package com.ai.offers.agent.demo.merchantofferai.model;

import java.time.LocalDateTime;

public class Incident {

    private String incidentId;
    private String offerId;
    private String title;
    private String issueType;
    private String description;
    private IncidentPriority currentPriority;
    private IncidentPriority suggestedPriority;
    private String rootCause;
    private String resolution;
    private String rca;
    private boolean defectRequired;
    private String defectId;
    private boolean duplicate;
    private String linkedIncidentId;
    private String status;
    private LocalDateTime createdTime;

    public Incident() {
    }

    public Incident(String incidentId, String offerId, String title, String issueType,
                    String description, IncidentPriority currentPriority,
                    IncidentPriority suggestedPriority, String rootCause,
                    String resolution, String rca, boolean defectRequired,
                    String defectId, boolean duplicate, String linkedIncidentId,
                    String status, LocalDateTime createdTime) {
        this.incidentId = incidentId;
        this.offerId = offerId;
        this.title = title;
        this.issueType = issueType;
        this.description = description;
        this.currentPriority = currentPriority;
        this.suggestedPriority = suggestedPriority;
        this.rootCause = rootCause;
        this.resolution = resolution;
        this.rca = rca;
        this.defectRequired = defectRequired;
        this.defectId = defectId;
        this.duplicate = duplicate;
        this.linkedIncidentId = linkedIncidentId;
        this.status = status;
        this.createdTime = createdTime;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IncidentPriority getCurrentPriority() {
        return currentPriority;
    }

    public void setCurrentPriority(IncidentPriority currentPriority) {
        this.currentPriority = currentPriority;
    }

    public IncidentPriority getSuggestedPriority() {
        return suggestedPriority;
    }

    public void setSuggestedPriority(IncidentPriority suggestedPriority) {
        this.suggestedPriority = suggestedPriority;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getRca() {
        return rca;
    }

    public void setRca(String rca) {
        this.rca = rca;
    }

    public boolean isDefectRequired() {
        return defectRequired;
    }

    public void setDefectRequired(boolean defectRequired) {
        this.defectRequired = defectRequired;
    }

    public String getDefectId() {
        return defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public String getLinkedIncidentId() {
        return linkedIncidentId;
    }

    public void setLinkedIncidentId(String linkedIncidentId) {
        this.linkedIncidentId = linkedIncidentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}