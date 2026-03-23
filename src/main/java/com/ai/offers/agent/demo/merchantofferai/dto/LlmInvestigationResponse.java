package com.ai.offers.agent.demo.merchantofferai.dto;

import java.util.List;

public class LlmInvestigationResponse {

    private String issue;
    private String rootCause;
    private String resolution;
    private String rca;
    private String priorityRecommendation;
    private String defectRecommendation;
    private List<String> nextSteps;
    private Evidence evidence;

    public LlmInvestigationResponse() {
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
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

    public String getPriorityRecommendation() {
        return priorityRecommendation;
    }

    public void setPriorityRecommendation(String priorityRecommendation) {
        this.priorityRecommendation = priorityRecommendation;
    }

    public String getDefectRecommendation() {
        return defectRecommendation;
    }

    public void setDefectRecommendation(String defectRecommendation) {
        this.defectRecommendation = defectRecommendation;
    }

    public List<String> getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(List<String> nextSteps) {
        this.nextSteps = nextSteps;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public static class Evidence {
        private List<String> offerFieldsUsed;
        private List<String> logLinesUsed;
        private List<String> rulesApplied;
        private List<String> incidentSignalsUsed;

        public Evidence() {
        }

        public List<String> getOfferFieldsUsed() {
            return offerFieldsUsed;
        }

        public void setOfferFieldsUsed(List<String> offerFieldsUsed) {
            this.offerFieldsUsed = offerFieldsUsed;
        }

        public List<String> getLogLinesUsed() {
            return logLinesUsed;
        }

        public void setLogLinesUsed(List<String> logLinesUsed) {
            this.logLinesUsed = logLinesUsed;
        }

        public List<String> getRulesApplied() {
            return rulesApplied;
        }

        public void setRulesApplied(List<String> rulesApplied) {
            this.rulesApplied = rulesApplied;
        }

        public List<String> getIncidentSignalsUsed() {
            return incidentSignalsUsed;
        }

        public void setIncidentSignalsUsed(List<String> incidentSignalsUsed) {
            this.incidentSignalsUsed = incidentSignalsUsed;
        }
    }
}