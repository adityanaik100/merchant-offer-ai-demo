package com.ai.offers.agent.demo.merchantofferai.controller;

import com.ai.offers.agent.demo.merchantofferai.model.Incident;
import com.ai.offers.agent.demo.merchantofferai.service.IncidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @GetMapping("/{incidentId}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable String incidentId) {
        return incidentService.getIncidentById(incidentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/offer/{offerId}")
    public List<Incident> getIncidentsByOfferId(@PathVariable String offerId) {
        return incidentService.getIncidentsByOfferId(offerId);
    }
}