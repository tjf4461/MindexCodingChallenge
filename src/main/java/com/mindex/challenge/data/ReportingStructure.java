package com.mindex.challenge.data;

/**
 * This class represents an employee reporting hierarchy within a company.
 * 
 * @author Thomas Fuller
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    /**
     * The constructor for the ReportingStructure.
     */
    public ReportingStructure() {
    }

    /**
     * Sets the employee of this report to the given value
     * @param employee: the employee object corresponding to this report structure
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Sets the number of reports to the given value
     * @param num: the number of employees that report to the employee for this report structure
     */
    public void setNumberOfReports(int num) {
        this.numberOfReports = num;
    }

    /**
     * Returns the employee for this report structure.
     * @return Employee: the employee object
     */
    public Employee getEmployee() {
        return this.employee;
    }

    /**
     * Returns the number of employees that report to the employee for this structure.
     * @return int: number of reports
     */
    public int getNumberOfReports() {
        return this.numberOfReports;
    }
}
