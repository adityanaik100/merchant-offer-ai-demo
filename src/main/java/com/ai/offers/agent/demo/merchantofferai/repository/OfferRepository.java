package com.ai.offers.agent.demo.merchantofferai.repository;

import com.ai.offers.agent.demo.merchantofferai.model.Offer;
import com.ai.offers.agent.demo.merchantofferai.model.OfferStatus;
import com.ai.offers.agent.demo.merchantofferai.model.OfferType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class OfferRepository {

    private final Map<String, Offer> offers = new HashMap<>();

    @PostConstruct
    public void init() {
        offers.put("OF1001", new Offer(
                "OF1001",
                "PARTNER_2",
                "Starbucks",
                "CMP1001",
                OfferType.PARTNER_FUNDED,
                OfferStatus.ONBOARDING_IN_PROGRESS,
                "BUSINESS",
                150.0,
                20.0,
                LocalDateTime.now().minusDays(3),
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(30),
                "Gold Business Users",
                true,
                "OfferOnboardingService",
                    "ConfirmationService",
                "Offer stuck before submission due to downstream confirmation pending"
        ));

        offers.put("OF1002", new Offer(
                "OF1002",
                "PARTNER_1",
                "Long Merchant Name Beyond Fifty Characters Demo Store ABC",
                "CMP1002",
                OfferType.PARTNER_FUNDED,
                OfferStatus.FAILED,
                "CONSUMER",
                75.0,
                10.0,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(20),
                "Consumer Card Users",
                true,
                "MerchantIntegrationService",
                "DownstreamPartnerSystem",
                "Merchant name length issue causing downstream failure"
        ));

        offers.put("OF1003", new Offer(
                "OF1003",
                "PARTNER_3",
                "Best Buy",
                "CMP1003",
                OfferType.CARD_LINKED,
                OfferStatus.ACTIVE,
                "BUSINESS",
                50.0,
                5.0,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(25),
                "Business Card Users",
                false,
                "TargetingService",
                "CampaignServingSystem",
                "Offer active but targeting applied incorrectly to consumer cards"
        ));

        offers.put("OF1004", new Offer(
                "OF1004",
                "INTERNAL",
                "Hilton",
                "CMP1004",
                OfferType.INTERNAL,
                OfferStatus.SUBMITTED,
                "CONSUMER",
                200.0,
                30.0,
                LocalDateTime.now().minusDays(15),
                LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(60),
                "Premium Consumer Users",
                false,
                "OfferSubmissionService",
                "CampaignServingSystem",
                "Healthy submitted offer for success scenario"
        ));
    }

    public List<Offer> findAll() {
        return new ArrayList<>(offers.values());
    }

    public Optional<Offer> findById(String offerId) {
        return Optional.ofNullable(offers.get(offerId));
    }

    public void save(Offer offer) {
        offers.put(offer.getOfferId(), offer);
    }
}