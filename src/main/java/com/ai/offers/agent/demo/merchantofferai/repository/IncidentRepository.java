package com.ai.offers.agent.demo.merchantofferai.repository;

import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.model.IncidentPriority;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class IncidentRepository {

    private final Map<String, Incident> incidents = new HashMap<>();

    @PostConstruct
    public void init() {
        incidents.put("INC1001", new Incident(
                "INC1001",
                "OF1001",
                "Unable to submit offer",
                "STATUS_MISMATCH",
                "User is unable to submit offer from current status",
                IncidentPriority.HIGH,
                IncidentPriority.CRITICAL,
                "Offer is still in onboarding state and has not moved to confirmation state",
                "Wait for downstream confirmation or manually correct status after verification",
                "Submission blocked due to invalid lifecycle transition",
                true,
                "DEF1001",
                false,
                null,
                "OPEN",
                LocalDateTime.now().minusHours(5)
        ));

        incidents.put("INC1002", new Incident(
                "INC1002",
                "OF1002",
                "Offer failed in downstream system",
                "DOWNSTREAM_SCHEMA_FAILURE",
                "Merchant name caused downstream validation failure",
                IncidentPriority.MEDIUM,
                IncidentPriority.HIGH,
                "Merchant name length exceeded downstream schema limit of 50",
                "Update merchant name from backend and raise schema alignment defect",
                "Producer accepts more characters than downstream supports",
                true,
                "DEF1002",
                false,
                null,
                "OPEN",
                LocalDateTime.now().minusHours(7)
        ));

        incidents.put("INC1003", new Incident(
                "INC1003",
                "OF1003",
                "Business offer visible for consumer cards",
                "TARGETING_BUG",
                "Incorrect targeting behavior for specific offer type",
                IncidentPriority.HIGH,
                IncidentPriority.HIGH,
                "Card type targeting not set correctly from UI for this offer type",
                "Apply backend fix and raise defect for UI mapping issue",
                "Offer configuration valid, but UI payload mapping is incorrect",
                true,
                "DEF1003",
                false,
                null,
                "OPEN",
                LocalDateTime.now().minusHours(9)
        ));

        incidents.put("INC1004", new Incident(
                "INC1004",
                "OF1003",
                "Duplicate targeting incident",
                "TARGETING_BUG",
                "Same targeting issue reported again",
                IncidentPriority.MEDIUM,
                IncidentPriority.HIGH,
                "Duplicate of existing targeting bug for same offer",
                "Link with existing defect and incident",
                "Multiple incidents represent same underlying issue",
                false,
                "DEF1003",
                true,
                "INC1003",
                "OPEN",
                LocalDateTime.now().minusHours(2)
        ));
    }

    public List<Incident> findAll() {
        return new ArrayList<>(incidents.values());
    }

    public Optional<Incident> findById(String incidentId) {
        return Optional.ofNullable(incidents.get(incidentId));
    }

    public List<Incident> findByOfferId(String offerId) {
        return incidents.values().stream()
                .filter(incident -> incident.getOfferId().equalsIgnoreCase(offerId))
                .collect(Collectors.toList());
    }

    public void save(Incident incident) {
        incidents.put(incident.getIncidentId(), incident);
    }
}