package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.model.Campaign;
import com.ai.offers.agent.demo.merchantofferai.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Optional<Campaign> getCampaignById(String campaignId) {
        return campaignRepository.findById(campaignId);
    }

    public void saveCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
    }
}