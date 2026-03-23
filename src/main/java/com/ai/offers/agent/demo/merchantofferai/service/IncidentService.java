package com.ai.offers.agent.demo.merchantofferai.service;

import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Optional<Incident> getIncidentById(String incidentId) {
        return incidentRepository.findById(incidentId);
    }

    public List<Incident> getIncidentsByOfferId(String offerId) {
        return incidentRepository.findByOfferId(offerId);
    }

    public void saveIncident(Incident incident) {
        incidentRepository.save(incident);
    }

    public List<Incident> getOpenIncidents() {
        return incidentRepository.findAll().stream()
                .filter(incident -> "OPEN".equalsIgnoreCase(incident.getStatus()))
                .toList();
    }
}