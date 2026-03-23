package com.ai.offers.agent.demo.merchantofferai.dto;

import java.util.List;

public class ProactiveDetectionResponse {

    private String executiveSummary;
    private List<DetectedIssue> detectedIssues;
    private List<String> observabilityGaps;
    private List<String> recommendedActions;
    private Evidence evidence;

    public ProactiveDetectionResponse() {
    }

    public String getExecutiveSummary() {
        return executiveSummary;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }

    public List<DetectedIssue> getDetectedIssues() {
        return detectedIssues;
    }

    public void setDetectedIssues(List<DetectedIssue> detectedIssues) {
        this.detectedIssues = detectedIssues;
    }

    public List<String> getObservabilityGaps() {
        return observabilityGaps;
    }

    public void setObservabilityGaps(List<String> observabilityGaps) {
        this.observabilityGaps = observabilityGaps;
    }

    public List<String> getRecommendedActions() {
        return recommendedActions;
    }

    public void setRecommendedActions(List<String> recommendedActions) {
        this.recommendedActions = recommendedActions;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public static class DetectedIssue {
        private String offerId;
        private String issueSummary;
        private String recommendedAction;
        private String prioritySuggestion;
        private String confidence;
        private boolean incidentAlreadyExists;

        public DetectedIssue() {
        }

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public String getIssueSummary() {
            return issueSummary;
        }

        public void setIssueSummary(String issueSummary) {
            this.issueSummary = issueSummary;
        }

        public String getRecommendedAction() {
            return recommendedAction;
        }

        public void setRecommendedAction(String recommendedAction) {
            this.recommendedAction = recommendedAction;
        }

        public String getPrioritySuggestion() {
            return prioritySuggestion;
        }

        public void setPrioritySuggestion(String prioritySuggestion) {
            this.prioritySuggestion = prioritySuggestion;
        }

        public String getConfidence() {
            return confidence;
        }

        public void setConfidence(String confidence) {
            this.confidence = confidence;
        }

        public boolean isIncidentAlreadyExists() {
            return incidentAlreadyExists;
        }

        public void setIncidentAlreadyExists(boolean incidentAlreadyExists) {
            this.incidentAlreadyExists = incidentAlreadyExists;
        }
    }

    public static class Evidence {
        private List<String> offersUsed;
        private List<String> logsUsed;
        private List<String> incidentsChecked;
        private List<String> rulesApplied;

        public Evidence() {
        }

        public List<String> getOffersUsed() {
            return offersUsed;
        }

        public void setOffersUsed(List<String> offersUsed) {
            this.offersUsed = offersUsed;
        }

        public List<String> getLogsUsed() {
            return logsUsed;
        }

        public void setLogsUsed(List<String> logsUsed) {
            this.logsUsed = logsUsed;
        }

        public List<String> getIncidentsChecked() {
            return incidentsChecked;
        }

        public void setIncidentsChecked(List<String> incidentsChecked) {
            this.incidentsChecked = incidentsChecked;
        }

        public List<String> getRulesApplied() {
            return rulesApplied;
        }

        public void setRulesApplied(List<String> rulesApplied) {
            this.rulesApplied = rulesApplied;
        }
    }
}