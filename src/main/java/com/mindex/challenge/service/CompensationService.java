package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * Interface for the CompensationService.
 * Includes endpoints needed to create and read Compensation objects.
 * 
 * @author Thomas Fuller
 */
public interface CompensationService {


    /**
     * Endpoint to read a Compensation object from the database.
     * url endpoint ends with /compensation/{id}
     */
    Compensation create(Compensation compensation);
    
    /**
     * Endpoint to read a Compensation object from the database.
     * url endpoint ends with /compensation/{id}
     */
    Compensation read(String id);
}
