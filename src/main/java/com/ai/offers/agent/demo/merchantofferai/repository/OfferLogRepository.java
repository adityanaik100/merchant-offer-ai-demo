package com.ai.offers.agent.demo.merchantofferai.repository;

import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OfferLogRepository {

    private final List<OfferLog> logs = new ArrayList<>();

    @PostConstruct
    public void init() {
        logs.add(new OfferLog("LOG1", "OF1001", "INFO", "OfferOnboardingService",
                "Offer created successfully and moved to onboarding state",
                LocalDateTime.now().minusDays(3)));

        logs.add(new OfferLog("LOG2", "OF1001", "ERROR", "ConfirmationService",
                "STATUS_VALIDATION_FAILED - expected CONFIRMATION_IN_PROGRESS before submission",
                LocalDateTime.now().minusHours(8)));

        logs.add(new OfferLog("LOG3", "OF1002", "ERROR", "DownstreamPartnerSystem",
                "MERCHANT_NAME_LENGTH_EXCEEDED - received merchant name length 58, supported max 50",
                LocalDateTime.now().minusDays(1)));

        logs.add(new OfferLog("LOG4", "OF1003", "WARN", "TargetingService",
                "Targeting attribute for card type not populated correctly from UI payload",
                LocalDateTime.now().minusHours(12)));

        logs.add(new OfferLog("LOG5", "OF1003", "INFO", "CampaignServingSystem",
                "Offer active and propagated to serving layer",
                LocalDateTime.now().minusHours(10)));

        logs.add(new OfferLog("LOG6", "OF1004", "INFO", "OfferSubmissionService",
                "Offer submitted successfully",
                LocalDateTime.now().minusDays(2)));

        logs.add(new OfferLog("LOG7", "OF1004", "INFO", "CampaignServingSystem",
                "Offer published successfully to downstream systems",
                LocalDateTime.now().minusDays(1)));

        logs.add(new OfferLog("LOG8", "OF1005", "WARN", "OfferValidationService",
                "Insufficient logs available to determine exact failure path",
                LocalDateTime.now().minusHours(6)));
    }

    public List<OfferLog> findAll() {
        return logs;
    }

    public List<OfferLog> findByOfferId(String offerId) {
        return logs.stream()
                .filter(log -> log.getOfferId().equalsIgnoreCase(offerId))
                .collect(Collectors.toList());
    }

    public void saveAll(List<OfferLog> newLogs) {
        logs.addAll(newLogs);
    }
}