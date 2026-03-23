package com.ai.offers.agent.demo.merchantofferai.dto;

public class OperationalScenarioRequest {

    private String offerId;
    private String campaignId;
    private String partnerName;
    private String merchantName;
    private String offerType;
    private String cardType;
    private Double minSpend;
    private Double cashbackAmount;
    private String targetingSegment;
    private String currentStatus;
    private String expectedNextSystem;
    private boolean userSubmissionBlocked;
    private String logsMode;
    private String downstreamBehavior;
    private String targetingBehavior;
    private Integer startInDays;
    private Integer endInDays;

    public OperationalScenarioRequest() {
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

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Double getMinSpend() {
        return minSpend;
    }

    public void setMinSpend(Double minSpend) {
        this.minSpend = minSpend;
    }

    public Double getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(Double cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public String getTargetingSegment() {
        return targetingSegment;
    }

    public void setTargetingSegment(String targetingSegment) {
        this.targetingSegment = targetingSegment;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getExpectedNextSystem() {
        return expectedNextSystem;
    }

    public void setExpectedNextSystem(String expectedNextSystem) {
        this.expectedNextSystem = expectedNextSystem;
    }

    public boolean isUserSubmissionBlocked() {
        return userSubmissionBlocked;
    }

    public void setUserSubmissionBlocked(boolean userSubmissionBlocked) {
        this.userSubmissionBlocked = userSubmissionBlocked;
    }

    public String getLogsMode() {
        return logsMode;
    }

    public void setLogsMode(String logsMode) {
        this.logsMode = logsMode;
    }

    public String getDownstreamBehavior() {
        return downstreamBehavior;
    }

    public void setDownstreamBehavior(String downstreamBehavior) {
        this.downstreamBehavior = downstreamBehavior;
    }

    public String getTargetingBehavior() {
        return targetingBehavior;
    }

    public void setTargetingBehavior(String targetingBehavior) {
        this.targetingBehavior = targetingBehavior;
    }

    public Integer getStartInDays() {
        return startInDays;
    }

    public void setStartInDays(Integer startInDays) {
        this.startInDays = startInDays;
    }

    public Integer getEndInDays() {
        return endInDays;
    }

    public void setEndInDays(Integer endInDays) {
        this.endInDays = endInDays;
    }
}