package com.ai.offers.agent.demo.merchantofferai.model;

import java.time.LocalDateTime;

public class Campaign {

    private String campaignId;
    private String campaignName;
    private String partnerName;
    private LocalDateTime createdTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String objective;

    public Campaign() {
    }

    public Campaign(String campaignId, String campaignName, String partnerName,
                    LocalDateTime createdTime, LocalDateTime startDate,
                    LocalDateTime endDate, String objective) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.partnerName = partnerName;
        this.createdTime = createdTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}