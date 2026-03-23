package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.model.RuleDefinition;
import com.ai.offers.agent.demo.merchantofferai.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public List<RuleDefinition> getAllRules() {
        return ruleRepository.findAll();
    }

    public List<RuleDefinition> getRulesForOfferType(String appliesTo) {
        return ruleRepository.findByAppliesTo(appliesTo);
    }
}