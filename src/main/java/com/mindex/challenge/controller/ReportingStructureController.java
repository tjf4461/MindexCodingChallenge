package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
/**
 * Controller for the ReportingStructure service.
 * Establishes the url for the endpoint. /reportingStructure/{id}
 */
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    @PostMapping("/reportingStructure/{id}")
    public ReportingStructure create(@PathVariable String id, @RequestBody ReportingStructure reportStructure) {
        LOG.debug("Received reporting structure create request for [{}]", id);

        return reportingStructureService.create(reportStructure, id);
    }
}
