package com.ai.offers.agent.demo.merchantofferai.model;

import java.time.LocalDateTime;

public class Offer {

    private String offerId;
    private String partnerName;
    private String merchantName;
    private String campaignId;
    private OfferType offerType;
    private OfferStatus status;
    private String cardType;
    private Double minSpend;
    private Double cashbackAmount;
    private LocalDateTime createdTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String targetingSegment;
    private boolean userSubmissionBlocked;
    private String currentSystem;
    private String expectedNextSystem;
    private String description;

    public Offer() {
    }

    public Offer(String offerId, String partnerName, String merchantName, String campaignId,
                 OfferType offerType, OfferStatus status, String cardType, Double minSpend,
                 Double cashbackAmount, LocalDateTime createdTime, LocalDateTime startDate,
                 LocalDateTime endDate, String targetingSegment, boolean userSubmissionBlocked,
                 String currentSystem, String expectedNextSystem, String description) {
        this.offerId = offerId;
        this.partnerName = partnerName;
        this.merchantName = merchantName;
        this.campaignId = campaignId;
        this.offerType = offerType;
        this.status = status;
        this.cardType = cardType;
        this.minSpend = minSpend;
        this.cashbackAmount = cashbackAmount;
        this.createdTime = createdTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetingSegment = targetingSegment;
        this.userSubmissionBlocked = userSubmissionBlocked;
        this.currentSystem = currentSystem;
        this.expectedNextSystem = expectedNextSystem;
        this.description = description;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
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

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
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

    public String getTargetingSegment() {
        return targetingSegment;
    }

    public void setTargetingSegment(String targetingSegment) {
        this.targetingSegment = targetingSegment;
    }

    public boolean isUserSubmissionBlocked() {
        return userSubmissionBlocked;
    }

    public void setUserSubmissionBlocked(boolean userSubmissionBlocked) {
        this.userSubmissionBlocked = userSubmissionBlocked;
    }

    public String getCurrentSystem() {
        return currentSystem;
    }

    public void setCurrentSystem(String currentSystem) {
        this.currentSystem = currentSystem;
    }

    public String getExpectedNextSystem() {
        return expectedNextSystem;
    }

    public void setExpectedNextSystem(String expectedNextSystem) {
        this.expectedNextSystem = expectedNextSystem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}