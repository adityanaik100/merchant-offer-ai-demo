package com.ai.offers.agent.demo.merchantofferai.model;

public class RuleDefinition {

    private String ruleId;
    private String category;
    private String title;
    private String description;
    private String severity;
    private String appliesTo;

    public RuleDefinition() {
    }

    public RuleDefinition(String ruleId, String category, String title,
                          String description, String severity, String appliesTo) {
        this.ruleId = ruleId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.appliesTo = appliesTo;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
    }
}