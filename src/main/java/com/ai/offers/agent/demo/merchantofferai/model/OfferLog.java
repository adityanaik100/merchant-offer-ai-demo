package com.ai.offers.agent.demo.merchantofferai.model;

import java.time.LocalDateTime;

public class OfferLog {

    private String logId;
    private String offerId;
    private String level;
    private String sourceSystem;
    private String message;
    private LocalDateTime timestamp;

    public OfferLog() {
    }

    public OfferLog(String logId, String offerId, String level, String sourceSystem,
                    String message, LocalDateTime timestamp) {
        this.logId = logId;
        this.offerId = offerId;
        this.level = level;
        this.sourceSystem = sourceSystem;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}