package com.ai.offers.agent.demo.merchantofferai.agent;

import com.ai.offers.agent.demo.merchantofferai.dto.ProactiveDetectionContext;
import com.ai.offers.agent.demo.merchantofferai.dto.ProactiveDetectionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ai.offers.agent.demo.merchantofferai.util.JsonExtractionUtil;

@Service
public class OpenAiProactiveDetectionService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.url}")
    private String openAiUrl;

    public OpenAiProactiveDetectionService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ProactiveDetectionResponse analyze(ProactiveDetectionContext context) {
        if (openAiApiKey == null || openAiApiKey.isBlank() || "YOUR_OPENAI_API_KEY".equals(openAiApiKey)) {
            ProactiveDetectionResponse fallback = new ProactiveDetectionResponse();
            fallback.setExecutiveSummary("Proactive Defect Detection Agent could not run because OpenAI API key is not configured.");
            fallback.setRecommendedActions(List.of(
                    "Add a valid openai.api.key in application.properties",
                    "Restart the application",
                    "Retry the Proactive Defect Detection Dashboard"
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
            ProactiveDetectionResponse fallback = new ProactiveDetectionResponse();
            fallback.setExecutiveSummary("LLM-based proactive detection analysis could not be completed.");
            fallback.setRecommendedActions(List.of(
                    "Verify OpenAI configuration",
                    "Check internet connectivity",
                    "Inspect parsing logic",
                    "Retry proactive detection analysis"
            ));
            return fallback;
        }
    }

    private String buildInstructions() {
        return """
            You are a Proactive Defect Detection Agent for an enterprise merchant-offer platform.

            Analyze the provided offers, logs, incidents, and rules to identify hidden, emerging, or weakly reported operational issues.

            Your responsibilities are to:
            - detect issue candidates from offer state, logs, and system signals
            - identify cases where incident creation or escalation should be recommended
            - identify observability gaps
            - recommend follow-up actions
            - provide evidence for your conclusions

            Use only the provided evidence.
            Do not invent facts.
            If evidence is insufficient, say so clearly.

            Return STRICT JSON with exactly this structure:
            {
              "executiveSummary": "string",
              "detectedIssues": [
                {
                  "offerId": "string",
                  "issueSummary": "string",
                  "recommendedAction": "string",
                  "prioritySuggestion": "string",
                  "confidence": "string",
                  "incidentAlreadyExists": true
                }
              ],
              "observabilityGaps": ["string"],
              "recommendedActions": ["string"],
              "evidence": {
                "offersUsed": ["string"],
                "logsUsed": ["string"],
                "incidentsChecked": ["string"],
                "rulesApplied": ["string"]
              }
            }

            Detection guidance:
            - surface blocked lifecycle issues
            - surface downstream validation or propagation issues
            - surface targeting anomalies
            - surface near-launch unresolved risks
            - surface insufficient logging or poor observability
            - indicate whether an incident already appears to exist for the offer

            Return valid JSON only.
            """;
    }

    private String buildInput(String contextJson) {
        return """
                Analyze the following merchant-offer operational data and identify hidden or emerging issues.

                Context:
                """ + contextJson;
    }

    private ProactiveDetectionResponse parseResponse(String rawResponse) throws JsonProcessingException {
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
        return objectMapper.readValue(json, ProactiveDetectionResponse.class);
    }
}