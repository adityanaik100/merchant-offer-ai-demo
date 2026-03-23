package com.ai.offers.agent.demo.merchantofferai.agent;

import com.ai.offers.agent.demo.merchantofferai.dto.InvestigationContext;
import com.ai.offers.agent.demo.merchantofferai.dto.LlmInvestigationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ai.offers.agent.demo.merchantofferai.util.JsonExtractionUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAiInvestigationService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.url}")
    private String openAiUrl;

    public OpenAiInvestigationService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public LlmInvestigationResponse analyze(InvestigationContext context) {
        if (openAiApiKey == null || openAiApiKey.isBlank() || "YOUR_OPENAI_API_KEY".equals(openAiApiKey)) {
            LlmInvestigationResponse fallback = new LlmInvestigationResponse();
            fallback.setIssue("OpenAI API key is not configured");
            fallback.setRootCause("The investigation agent cannot call the language model without a valid API key");
            fallback.setResolution("Set openai.api.key in application.properties before running the AI investigation");
            fallback.setRca("Context retrieval succeeded, but model execution was skipped due to missing configuration");
            fallback.setPriorityRecommendation("MEDIUM");
            fallback.setDefectRecommendation("No defect recommendation generated because the AI model was not invoked");
            fallback.setNextSteps(java.util.List.of(
                    "Add a valid openai.api.key",
                    "Restart the application",
                    "Retry the incident investigation"
            ));
            return fallback;
        }
        try {
            String contextJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(context);
            String instructions = buildSystemInstructions();
            String input = buildUserPrompt(contextJson);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openAiApiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", model);
            requestBody.put("instructions", instructions);
            requestBody.put("input", input);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    openAiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            return parseResponse(response.getBody());

        } catch (Exception e) {
            LlmInvestigationResponse fallback = new LlmInvestigationResponse();
            fallback.setIssue("LLM analysis failed");
            fallback.setRootCause("Unable to complete model-based investigation");
            fallback.setResolution("Check OpenAI configuration, request payload, or API connectivity");
            fallback.setRca("The investigation context was assembled, but the model response could not be parsed or retrieved");
            fallback.setPriorityRecommendation("MEDIUM");
            fallback.setDefectRecommendation("No defect recommendation generated because LLM call failed");
            fallback.setNextSteps(java.util.List.of(
                    "Verify openai.api.key",
                    "Verify model name",
                    "Verify internet connectivity",
                    "Inspect raw API response for parsing issues"
            ));
            LlmInvestigationResponse.Evidence evidence = new LlmInvestigationResponse.Evidence();
            evidence.setOfferFieldsUsed(java.util.List.of("Fallback response - no LLM evidence available"));
            evidence.setLogLinesUsed(java.util.List.of());
            evidence.setRulesApplied(java.util.List.of());
            evidence.setIncidentSignalsUsed(java.util.List.of());
            fallback.setEvidence(evidence);
            return fallback;
        }
    }

    private String buildSystemInstructions() {
        return """
            You are an Incident Investigation Agent for an enterprise merchant-offer platform.

            Your job is to investigate a specific incident by analyzing the provided:
            - offer data
            - campaign data
            - logs
            - incident records
            - business rules
            - operational guidance

            Use only the provided evidence.
            Do not invent facts.
            Do not assume missing information.
            If evidence is insufficient, explicitly say so.

            You must return a STRICT JSON object with exactly these top-level fields:
            {
              "issue": "string",
              "rootCause": "string",
              "resolution": "string",
              "rca": "string",
              "priorityRecommendation": "string",
              "defectRecommendation": "string",
              "nextSteps": ["string"],
              "evidence": {
                "offerFieldsUsed": ["string"],
                "logLinesUsed": ["string"],
                "rulesApplied": ["string"],
                "incidentSignalsUsed": ["string"]
              }
            }

            Investigation expectations:
            - Analyze lifecycle transition issues
            - Analyze targeting mismatches
            - Analyze downstream schema or integration failures
            - Analyze missing observability / insufficient logs
            - Analyze duplicate incident patterns if visible in the context
            - Consider launch proximity and blocked-user impact for priority recommendation
            - Recommend workaround vs permanent fix where appropriate

            Return only valid JSON. Do not wrap the JSON in markdown.
            """;
    }

    private String buildUserPrompt(String contextJson) {
        return """
                Investigate the following merchant-offer operational context and produce a structured incident analysis.

                Context:
                """ + contextJson;
    }

    private LlmInvestigationResponse parseResponse(String rawResponse) throws JsonProcessingException {
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
        return objectMapper.readValue(json, LlmInvestigationResponse.class);
    }
}