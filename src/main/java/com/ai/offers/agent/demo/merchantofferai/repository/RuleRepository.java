package com.ai.offers.agent.demo.merchantofferai.repository;

import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RuleRepository {

    private final List<RuleDefinition> rules = new ArrayList<>();

    @PostConstruct
    public void init() {
        rules.add(new RuleDefinition(
                "RULE-001",
                "LIFECYCLE",
                "Offer submission requires confirmation state",
                "An offer must reach CONFIRMATION_IN_PROGRESS before it can move to SUBMITTED.",
                "HIGH",
                "ALL_OFFERS"
        ));

        rules.add(new RuleDefinition(
                "RULE-002",
                "DOWNSTREAM_SCHEMA",
                "Merchant name downstream limit",
                "Downstream partner system supports merchant name length up to 50 characters.",
                "HIGH",
                "PARTNER_FUNDED"
        ));

        rules.add(new RuleDefinition(
                "RULE-003",
                "TARGETING",
                "Business card targeting enforcement",
                "Offers targeted to business cards must not be served to consumer card users.",
                "HIGH",
                "CARD_LINKED"
        ));

        rules.add(new RuleDefinition(
                "RULE-004",
                "OBSERVABILITY",
                "Insufficient logs must be flagged explicitly",
                "If logs are insufficient to isolate the failure, the investigation must recommend improved logging and lower-environment replication.",
                "MEDIUM",
                "ALL_OFFERS"
        ));

        rules.add(new RuleDefinition(
                "RULE-005",
                "INCIDENT_PRIORITY",
                "Priority depends on launch proximity and user impact",
                "Incidents affecting blocked users or offers starting within 2 days should be treated with elevated priority.",
                "HIGH",
                "ALL_INCIDENTS"
        ));

        rules.add(new RuleDefinition(
                "RULE-006",
                "DEFECT_MANAGEMENT",
                "DB workaround requires permanent fix tracking",
                "If an issue is temporarily mitigated through backend or database correction, a defect and follow-up story must be tracked.",
                "MEDIUM",
                "ALL_INCIDENTS"
        ));

        rules.add(new RuleDefinition(
                "RULE-007",
                "DUPLICATE_INCIDENT",
                "Duplicate incidents should link to one root defect",
                "Multiple incidents representing the same issue should be clustered and linked to one primary defect or parent incident.",
                "MEDIUM",
                "ALL_INCIDENTS"
        ));

        rules.add(new RuleDefinition(
                "RULE-008",
                "VALIDATION",
                "Offer lifecycle and payload constraints must be validated before downstream propagation",
                "Offer data should be validated against known downstream constraints and lifecycle dependencies before submission.",
                "HIGH",
                "ALL_OFFERS"
        ));
    }

    public List<RuleDefinition> findAll() {
        return new ArrayList<>(rules);
    }

    public List<RuleDefinition> findByAppliesTo(String appliesTo) {
        return rules.stream()
                .filter(rule -> rule.getAppliesTo().equalsIgnoreCase(appliesTo)
                        || rule.getAppliesTo().equalsIgnoreCase("ALL_OFFERS")
                        || rule.getAppliesTo().equalsIgnoreCase("ALL_INCIDENTS"))
                .collect(Collectors.toList());
    }
}