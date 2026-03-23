package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.model.OfferLog;
import com.ai.offers.agent.demo.merchantofferai.repository.OfferLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferLogService {

    private final OfferLogRepository offerLogRepository;

    public OfferLogService(OfferLogRepository offerLogRepository) {
        this.offerLogRepository = offerLogRepository;
    }

    public List<OfferLog> getAllLogs() {
        return offerLogRepository.findAll();
    }

    public List<OfferLog> getLogsByOfferId(String offerId) {
        return offerLogRepository.findByOfferId(offerId);
    }

    public void saveLogs(List<OfferLog> logs) {
        offerLogRepository.saveAll(logs);
    }
}