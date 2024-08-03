package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

@Service
/**
 * Implementation for the CompensationService.
 * Contains the endpoints for creating and reading compensation objects from the database.
 * 
 * @author Thomas Fuller
 */
public class CompensationServiceImpl implements CompensationService{
    
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    /**
     * Endpoint to create a Compensation object and insert it in the database.
     * url endpoint ends with /compensation
     */
    public Compensation create(Compensation compensation) {
        String id = compensation.getEmployee().getEmployeeId();
        LOG.debug("Creating compensation for employee [{}]", id);

        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    /**
     * Endpoint to read a Compensation object from the database.
     * url endpoint ends with /compensation/{id}
     */
    public Compensation read(String id) {
        LOG.debug("Reading compensation with employee id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        Compensation compensation;
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        } else {
            compensation = compensationRepository.findByEmployee(employee);
        }

        return compensation;
    }
}
