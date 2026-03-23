package com.ai.offers.agent.demo.merchantofferai.repository;

import com.ai.offers.agent.demo.merchantofferai.model.Campaign;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class CampaignRepository {

    private final Map<String, Campaign> campaigns = new HashMap<>();

    @PostConstruct
    public void init() {
        campaigns.put("CMP1001", new Campaign(
                "CMP1001",
                "Starbucks Business Boost",
                "Rakuten",
                LocalDateTime.now().minusDays(3),
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(30),
                "Drive business card weekday spend"
        ));

        campaigns.put("CMP1002", new Campaign(
                "CMP1002",
                "Merchant Name Constraint Campaign",
                "CDLX",
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(20),
                "Partner funded onboarding"
        ));

        campaigns.put("CMP1003", new Campaign(
                "CMP1003",
                "Business Card Exclusive Electronics",
                "Krowd",
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(25),
                "Target only business card users"
        ));

        campaigns.put("CMP1004", new Campaign(
                "CMP1004",
                "Hilton Premium Travel",
                "AMEX_INTERNAL",
                LocalDateTime.now().minusDays(15),
                LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(60),
                "Travel engagement and premium activation"
        ));
    }

    public List<Campaign> findAll() {
        return new ArrayList<>(campaigns.values());
    }

    public Optional<Campaign> findById(String campaignId) {
        return Optional.ofNullable(campaigns.get(campaignId));
    }

    public void save(Campaign campaign) {
        campaigns.put(campaign.getCampaignId(), campaign);
    }
}