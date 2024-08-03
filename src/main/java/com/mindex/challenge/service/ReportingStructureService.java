package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * Interface for the ReportingStructureService.
 * Includes endpoints needed to create ReportingStructures.
 * 
 * @author Thomas Fuller
 */
public interface ReportingStructureService {
    
    /**
     * Endpoint to create a ReportingStructure for the employee with the given id.
     * url endpoint ends with /reportingStructure/{id}
     */
    ReportingStructure create(ReportingStructure reportStructure, String id);
}
