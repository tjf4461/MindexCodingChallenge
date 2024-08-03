package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * Implementation for the ReportingStructureService.
 * Contains the endpoint for creating ReportingStructures for given employees.
 * 
 * @author Thomas Fuller
 */
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    /**
     * Endpoint to create a ReportingStructure for the employee with the given id.
     * url endpoint ends with /reportingStructure/{id}
     */
    public ReportingStructure create(ReportingStructure reportStructure, String id) {
        LOG.debug("Creating reporting structure for employee [{}]", id);

        // Find employee with the given ID in the repo
        Employee employee = employeeRepository.findByEmployeeId(id);

        // If the employee was found populate the report structure
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        } else {
            // Calculate and set the reporting data for the employee
            reportStructure.setEmployee(employee);
            int num = calculateNumberOfReports(employee);
            reportStructure.setNumberOfReports(num);
        }
        return reportStructure;
    }

    /**
     * This method calculates the total number of reporting employees to the given employee by navigating the reporting hierarchy.
     * It exists in this class in order to have access to the employee repository. The report list in the database for each employee
     * only refers to them by employee id, so to obtain the full tree we need access to the original Employee objects.
     * 
     * @param employee: the employee to calculate the number of reporting individuals to
     * @return int: the total number of employees that directly or indirectly report to this employee
     */
    public int calculateNumberOfReports(Employee employee) {
        int num = 0;
        // Null check before going through the reports
        if (employee.getDirectReports() != null) {
            for (Employee report : employee.getDirectReports()) {
                // Get full record for the reporting employee
                Employee reportingEmployee = employeeRepository.findByEmployeeId(report.getEmployeeId());
                // Recur through this employee's subordinates
                num += calculateNumberOfReports(reportingEmployee) + 1;
            }
        }
        return num;
    }
}