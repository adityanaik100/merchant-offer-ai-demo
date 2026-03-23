package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.dto.OperationalScenarioRequest;
import com.ai.offers.agent.demo.merchantofferai.dto.OperationalScenarioResponse;
import com.ai.offers.agent.demo.merchantofferai.model.Campaign;
import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.IncidentPriority;
import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.model.OfferStatus;
import com.ai.offers.agent.demo.merchantofferai.model.OfferType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OperationalScenarioService {

    private final OfferService offerService;
    private final CampaignService campaignService;
    private final OfferLogService offerLogService;
    private final IncidentService incidentService;

    public OperationalScenarioService(OfferService offerService,
                                      CampaignService campaignService,
                                      OfferLogService offerLogService,
                                      IncidentService incidentService) {
        this.offerService = offerService;
        this.campaignService = campaignService;
        this.offerLogService = offerLogService;
        this.incidentService = incidentService;
    }

    public OperationalScenarioResponse createScenario(OperationalScenarioRequest request) {
        String offerId = hasText(request.getOfferId()) ? request.getOfferId() : "OF" + System.currentTimeMillis();
        String campaignId = hasText(request.getCampaignId()) ? request.getCampaignId() : "CMP" + System.currentTimeMillis();
        String incidentId = "INC" + System.currentTimeMillis();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now.plusDays(request.getStartInDays() != null ? request.getStartInDays() : 2);
        LocalDateTime endDate = now.plusDays(request.getEndInDays() != null ? request.getEndInDays() : 30);

        Offer offer = new Offer(
                offerId,
                defaultValue(request.getPartnerName(), "DemoPartner"),
                defaultValue(request.getMerchantName(), "Demo Merchant"),
                campaignId,
                parseOfferType(request.getOfferType()),
                parseOfferStatus(request.getCurrentStatus()),
                defaultValue(request.getCardType(), "BUSINESS"),
                request.getMinSpend() != null ? request.getMinSpend() : 100.0,
                request.getCashbackAmount() != null ? request.getCashbackAmount() : 10.0,
                now,
                startDate,
                endDate,
                defaultValue(request.getTargetingSegment(), "Business Card Users"),
                request.isUserSubmissionBlocked(),
                "ScenarioSetupService",
                defaultValue(request.getExpectedNextSystem(), "ConfirmationService"),
                "Dynamically created operational scenario for AI investigation"
        );

        Campaign campaign = new Campaign(
                campaignId,
                defaultValue(request.getMerchantName(), "Demo Merchant") + " Campaign",
                defaultValue(request.getPartnerName(), "DemoPartner"),
                now,
                startDate,
                endDate,
                "Client demo scenario generation"
        );

        Incident incident = new Incident(
                incidentId,
                offerId,
                buildIncidentTitle(request),
                "AUTO_GENERATED_OPERATIONAL_INCIDENT",
                buildIncidentDescription(request),
                deriveInitialIncidentPriority(request),
                null,
                null,
                null,
                null,
                false,
                null,
                false,
                null,
                "OPEN",
                now
        );

        List<OfferLog> logs = generateLogs(request, offerId, now);

        offerService.saveOffer(offer);
        campaignService.saveCampaign(campaign);
        incidentService.saveIncident(incident);
        offerLogService.saveLogs(logs);

        return new OperationalScenarioResponse(
                offerId,
                campaignId,
                incidentId,
                "Operational scenario created successfully",
                List.of(
                        "Offer stored",
                        "Campaign stored",
                        "Incident stored from user symptom",
                        "Logs generated from operational conditions"
                )
        );
    }

    private List<OfferLog> generateLogs(OperationalScenarioRequest request, String offerId, LocalDateTime now) {
        List<OfferLog> logs = new ArrayList<>();

        logs.add(new OfferLog(
                UUID.randomUUID().toString(),
                offerId,
                "INFO",
                "ScenarioSetupService",
                "Offer scenario created dynamically for AI demo",
                now.minusMinutes(10)
        ));

        if (hasText(request.getLogsMode()) && request.getLogsMode().equalsIgnoreCase("LIMITED")) {
            logs.add(new OfferLog(
                    UUID.randomUUID().toString(),
                    offerId,
                    "WARN",
                    "OfferValidationService",
                    "Insufficient logs available to determine exact failure path",
                    now.minusMinutes(5)
            ));
        } else {
            logs.add(new OfferLog(
                    UUID.randomUUID().toString(),
                    offerId,
                    "INFO",
                    "OfferValidationService",
                    "Offer payload validation completed",
                    now.minusMinutes(6)
            ));
        }

        if (hasText(request.getDownstreamBehavior())) {
            if (request.getDownstreamBehavior().equalsIgnoreCase("FAILED_VALIDATION")) {
                logs.add(new OfferLog(
                        UUID.randomUUID().toString(),
                        offerId,
                        "ERROR",
                        "DownstreamPartnerSystem",
                        "Downstream validation failed while processing offer payload",
                        now.minusMinutes(4)
                ));
            } else if (request.getDownstreamBehavior().equalsIgnoreCase("NOT_TRIGGERED")) {
                logs.add(new OfferLog(
                        UUID.randomUUID().toString(),
                        offerId,
                        "WARN",
                        "DownstreamPartnerSystem",
                        "Expected downstream propagation was not triggered",
                        now.minusMinutes(4)
                ));
            } else {
                logs.add(new OfferLog(
                        UUID.randomUUID().toString(),
                        offerId,
                        "INFO",
                        "DownstreamPartnerSystem",
                        "Offer propagated successfully to downstream system",
                        now.minusMinutes(4)
                ));
            }
        }

        if (hasText(request.getTargetingBehavior())) {
            if (request.getTargetingBehavior().equalsIgnoreCase("INCORRECT_MAPPING")) {
                logs.add(new OfferLog(
                        UUID.randomUUID().toString(),
                        offerId,
                        "WARN",
                        "TargetingService",
                        "Targeting attribute for card type not populated correctly from request mapping",
                        now.minusMinutes(3)
                ));
            } else if (request.getTargetingBehavior().equalsIgnoreCase("MISSING_ATTRIBUTE")) {
                logs.add(new OfferLog(
                        UUID.randomUUID().toString(),
                        offerId,
                        "WARN",
                        "TargetingService",
                        "Required targeting attribute missing in incoming request payload",
                        now.minusMinutes(3)
                ));
            }
        }

        if (hasText(request.getCurrentStatus())
                && request.getCurrentStatus().equalsIgnoreCase("ONBOARDING_IN_PROGRESS")
                && request.isUserSubmissionBlocked()) {
            logs.add(new OfferLog(
                    UUID.randomUUID().toString(),
                    offerId,
                    "ERROR",
                    "ConfirmationService",
                    "STATUS_VALIDATION_FAILED - expected CONFIRMATION_IN_PROGRESS before submission",
                    now.minusMinutes(2)
            ));
        }

        if (request.getMerchantName() != null && request.getMerchantName().length() > 50) {
            logs.add(new OfferLog(
                    UUID.randomUUID().toString(),
                    offerId,
                    "ERROR",
                    "DownstreamPartnerSystem",
                    "MERCHANT_NAME_LENGTH_EXCEEDED - received merchant name length above downstream supported limit",
                    now.minusMinutes(1)
            ));
        }

        return logs;
    }

    private OfferType parseOfferType(String value) {
        if (!hasText(value)) {
            return OfferType.PARTNER_FUNDED;
        }
        return OfferType.valueOf(value.trim().toUpperCase());
    }

    private OfferStatus parseOfferStatus(String value) {
        if (!hasText(value)) {
            return OfferStatus.DRAFT;
        }
        return OfferStatus.valueOf(value.trim().toUpperCase());
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private String defaultValue(String value, String defaultValue) {
        return hasText(value) ? value : defaultValue;
    }

    private String buildIncidentTitle(OperationalScenarioRequest request) {
        if ("ONBOARDING_IN_PROGRESS".equalsIgnoreCase(request.getCurrentStatus()) && request.isUserSubmissionBlocked()) {
            return "Offer submission blocked";
        }

        if (request.getMerchantName() != null && request.getMerchantName().length() > 50) {
            return "Offer failed downstream validation";
        }

        if ("INCORRECT_MAPPING".equalsIgnoreCase(request.getTargetingBehavior())
                || "MISSING_ATTRIBUTE".equalsIgnoreCase(request.getTargetingBehavior())) {
            return "Offer targeting anomaly detected";
        }

        if ("LIMITED".equalsIgnoreCase(request.getLogsMode())) {
            return "Offer processing issue with limited observability";
        }

        return "Operational issue detected during offer setup";
    }

    private String buildIncidentDescription(OperationalScenarioRequest request) {
        if ("ONBOARDING_IN_PROGRESS".equalsIgnoreCase(request.getCurrentStatus()) && request.isUserSubmissionBlocked()) {
            return "Offer is currently blocked from submission while still in onboarding state.";
        }

        if (request.getMerchantName() != null && request.getMerchantName().length() > 50) {
            return "Offer payload may fail in downstream system due to merchant name length exceeding downstream limits.";
        }

        if ("INCORRECT_MAPPING".equalsIgnoreCase(request.getTargetingBehavior())) {
            return "Offer targeting appears inconsistent with expected card audience mapping.";
        }

        if ("MISSING_ATTRIBUTE".equalsIgnoreCase(request.getTargetingBehavior())) {
            return "Offer request payload appears to be missing a required targeting attribute.";
        }

        if ("FAILED_VALIDATION".equalsIgnoreCase(request.getDownstreamBehavior())) {
            return "Offer encountered downstream validation failure during setup flow.";
        }

        if ("NOT_TRIGGERED".equalsIgnoreCase(request.getDownstreamBehavior())) {
            return "Offer propagation to downstream system was not triggered as expected.";
        }

        if ("LIMITED".equalsIgnoreCase(request.getLogsMode())) {
            return "Offer issue detected, but available logs are insufficient for exact failure isolation.";
        }

        return "Operational issue auto-generated from setup conditions for monitoring and investigation.";
    }

    private IncidentPriority deriveInitialIncidentPriority(OperationalScenarioRequest request) {
        int score = 0;

        if (request.isUserSubmissionBlocked()) {
            score += 3;
        }

        if (request.getStartInDays() != null) {
            if (request.getStartInDays() <= 2) {
                score += 3;
            } else if (request.getStartInDays() <= 7) {
                score += 2;
            }
        }

        if ("FAILED_VALIDATION".equalsIgnoreCase(request.getDownstreamBehavior())
                || "NOT_TRIGGERED".equalsIgnoreCase(request.getDownstreamBehavior())) {
            score += 2;
        }

        if ("INCORRECT_MAPPING".equalsIgnoreCase(request.getTargetingBehavior())
                || "MISSING_ATTRIBUTE".equalsIgnoreCase(request.getTargetingBehavior())) {
            score += 2;
        }

        if (score >= 6) {
            return IncidentPriority.CRITICAL;
        } else if (score >= 4) {
            return IncidentPriority.HIGH;
        } else if (score >= 2) {
            return IncidentPriority.MEDIUM;
        }
        return IncidentPriority.LOW;
    }
}