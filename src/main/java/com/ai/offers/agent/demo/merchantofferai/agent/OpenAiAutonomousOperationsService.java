package com.ai.offers.agent.demo.merchantofferai.agent;

import com.ai.offers.agent.demo.merchantofferai.dto.AutonomousOperationsContext;
import com.ai.offers.agent.demo.merchantofferai.dto.AutonomousOperationsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ai.offers.agent.demo.merchantofferai.util.JsonExtractionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiAutonomousOperationsService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.url}")
    private String openAiUrl;

    public OpenAiAutonomousOperationsService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public AutonomousOperationsResponse analyze(AutonomousOperationsContext context) {
        if (openAiApiKey == null || openAiApiKey.isBlank() || "YOUR_OPENAI_API_KEY".equals(openAiApiKey)) {
            AutonomousOperationsResponse fallback = new AutonomousOperationsResponse();
            fallback.setExecutiveSummary("Incident Prioritization Agent could not run because OpenAI API key is not configured.");
            fallback.setRecommendedActions(List.of(
                    "Add a valid openai.api.key in application.properties",
                    "Restart the application",
                    "Retry the Incident Prioritization Dashboard"
            ));
            return fallback;
        }
        try {
            String contextJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(context);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openAiApiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", model);
            requestBody.put("instructions", buildInstructions());
            requestBody.put("input", buildInput(contextJson));

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    openAiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            return parseResponse(response.getBody());

        } catch (Exception e) {
            AutonomousOperationsResponse fallback = new AutonomousOperationsResponse();
            fallback.setExecutiveSummary("LLM-based autonomous operations analysis could not be completed.");
            fallback.setRecommendedActions(List.of(
                    "Verify OpenAI API configuration",
                    "Check internet connectivity",
                    "Inspect model response parsing",
                    "Retry the autonomous analysis request"
            ));
            return fallback;
        }
    }

    private String buildInstructions() {
        return """
            You are an Incident Prioritization Agent for an enterprise merchant-offer platform.

            Analyze the provided incident portfolio context across offers, incidents, logs, and rules.

            Your responsibilities are to:
            - identify incidents whose priority should be raised or corrected
            - identify likely duplicate incident clusters
            - identify operational risk alerts across offers and incidents
            - recommend concrete operational actions
            - provide evidence for your conclusions

            Use only the provided data.
            Do not invent incidents, rules, or logs.
            If evidence is insufficient, say so clearly.

            Return STRICT JSON only with exactly this structure:
            {
              "executiveSummary": "string",
              "priorityRecommendations": [
                {
                  "incidentId": "string",
                  "currentPriority": "string",
                  "suggestedPriority": "string",
                  "rationale": "string"
                }
              ],
              "duplicateClusters": [
                {
                  "clusterSummary": "string",
                  "incidentIds": ["string"],
                  "recommendedPrimaryIncident": "string",
                  "recommendedDefectId": "string",
                  "rationale": "string"
                }
              ],
              "riskAlerts": [
                {
                  "alertType": "string",
                  "impactedOfferId": "string",
                  "severity": "string",
                  "description": "string"
                }
              ],
              "recommendedActions": ["string"],
              "evidence": {
                "offersUsed": ["string"],
                "incidentsUsed": ["string"],
                "logsUsed": ["string"],
                "rulesApplied": ["string"]
              }
            }

            Priority guidance:
            - blocked user flows increase urgency
            - offer start date within 2 days increases urgency significantly
            - repeated incidents increase urgency
            - downstream production failures increase urgency

            Duplicate guidance:
            - if multiple incidents represent the same root problem, cluster them
            - recommend a primary incident and defect linkage where evidence supports it

            Risk guidance:
            - highlight blocked submissions
            - highlight downstream schema failures
            - highlight targeting issues
            - highlight missing observability or insufficient logs
            - highlight near-launch offers with unresolved incidents

            Return valid JSON only.
            """;
    }

    private String buildInput(String contextJson) {
        return """
                Analyze the following merchant-offer operational portfolio and produce structured autonomous-operations recommendations.

                Context:
                """ + contextJson;
    }

    private AutonomousOperationsResponse parseResponse(String rawResponse) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(rawResponse);

        String outputText = null;
        if (root.has("output")) {
            JsonNode outputArray = root.get("output");
            for (JsonNode outputItem : outputArray) {
                if (outputItem.has("content")) {
                    for (JsonNode contentItem : outputItem.get("content")) {
                        if (contentItem.has("text")) {
                            outputText = contentItem.get("text").asText();
                            break;
                        }
                    }
                }
                if (outputText != null) {
                    break;
                }
            }
        }

        if (outputText == null || outputText.isBlank()) {
            throw new IllegalStateException("No text content found in OpenAI response");
        }

        String json = JsonExtractionUtil.extractJsonObject(outputText);
        return objectMapper.readValue(json, AutonomousOperationsResponse.class);
    }
}