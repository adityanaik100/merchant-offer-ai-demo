package com.ai.offers.agent.demo.merchantofferai.dto;

import java.util.List;

public class AutonomousOperationsResponse {

    private String executiveSummary;
    private List<PriorityRecommendation> priorityRecommendations;
    private List<DuplicateCluster> duplicateClusters;
    private List<RiskAlert> riskAlerts;
    private List<String> recommendedActions;
    private Evidence evidence;

    public AutonomousOperationsResponse() {
    }

    public String getExecutiveSummary() {
        return executiveSummary;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }

    public List<PriorityRecommendation> getPriorityRecommendations() {
        return priorityRecommendations;
    }

    public void setPriorityRecommendations(List<PriorityRecommendation> priorityRecommendations) {
        this.priorityRecommendations = priorityRecommendations;
    }

    public List<DuplicateCluster> getDuplicateClusters() {
        return duplicateClusters;
    }

    public void setDuplicateClusters(List<DuplicateCluster> duplicateClusters) {
        this.duplicateClusters = duplicateClusters;
    }

    public List<RiskAlert> getRiskAlerts() {
        return riskAlerts;
    }

    public void setRiskAlerts(List<RiskAlert> riskAlerts) {
        this.riskAlerts = riskAlerts;
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

    public static class PriorityRecommendation {
        private String incidentId;
        private String currentPriority;
        private String suggestedPriority;
        private String rationale;

        public PriorityRecommendation() {
        }

        public String getIncidentId() {
            return incidentId;
        }

        public void setIncidentId(String incidentId) {
            this.incidentId = incidentId;
        }

        public String getCurrentPriority() {
            return currentPriority;
        }

        public void setCurrentPriority(String currentPriority) {
            this.currentPriority = currentPriority;
        }

        public String getSuggestedPriority() {
            return suggestedPriority;
        }

        public void setSuggestedPriority(String suggestedPriority) {
            this.suggestedPriority = suggestedPriority;
        }

        public String getRationale() {
            return rationale;
        }

        public void setRationale(String rationale) {
            this.rationale = rationale;
        }
    }

    public static class DuplicateCluster {
        private String clusterSummary;
        private List<String> incidentIds;
        private String recommendedPrimaryIncident;
        private String recommendedDefectId;
        private String rationale;

        public DuplicateCluster() {
        }

        public String getClusterSummary() {
            return clusterSummary;
        }

        public void setClusterSummary(String clusterSummary) {
            this.clusterSummary = clusterSummary;
        }

        public List<String> getIncidentIds() {
            return incidentIds;
        }

        public void setIncidentIds(List<String> incidentIds) {
            this.incidentIds = incidentIds;
        }

        public String getRecommendedPrimaryIncident() {
            return recommendedPrimaryIncident;
        }

        public void setRecommendedPrimaryIncident(String recommendedPrimaryIncident) {
            this.recommendedPrimaryIncident = recommendedPrimaryIncident;
        }

        public String getRecommendedDefectId() {
            return recommendedDefectId;
        }

        public void setRecommendedDefectId(String recommendedDefectId) {
            this.recommendedDefectId = recommendedDefectId;
        }

        public String getRationale() {
            return rationale;
        }

        public void setRationale(String rationale) {
            this.rationale = rationale;
        }
    }

    public static class RiskAlert {
        private String alertType;
        private String impactedOfferId;
        private String severity;
        private String description;

        public RiskAlert() {
        }

        public String getAlertType() {
            return alertType;
        }

        public void setAlertType(String alertType) {
            this.alertType = alertType;
        }

        public String getImpactedOfferId() {
            return impactedOfferId;
        }

        public void setImpactedOfferId(String impactedOfferId) {
            this.impactedOfferId = impactedOfferId;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class Evidence {
        private List<String> offersUsed;
        private List<String> incidentsUsed;
        private List<String> logsUsed;
        private List<String> rulesApplied;

        public Evidence() {
        }

        public List<String> getOffersUsed() {
            return offersUsed;
        }

        public void setOffersUsed(List<String> offersUsed) {
            this.offersUsed = offersUsed;
        }

        public List<String> getIncidentsUsed() {
            return incidentsUsed;
        }

        public void setIncidentsUsed(List<String> incidentsUsed) {
            this.incidentsUsed = incidentsUsed;
        }

        public List<String> getLogsUsed() {
            return logsUsed;
        }

        public void setLogsUsed(List<String> logsUsed) {
            this.logsUsed = logsUsed;
        }

        public List<String> getRulesApplied() {
            return rulesApplied;
        }

        public void setRulesApplied(List<String> rulesApplied) {
            this.rulesApplied = rulesApplied;
        }
    }
}