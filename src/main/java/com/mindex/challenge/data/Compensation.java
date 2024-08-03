package com.mindex.challenge.data;

/**
 * Class representing the compensation an employee receives.
 * 
 * @author Thomas Fuller
 */
public class Compensation {
    private Employee employee;
    private int salary;
    private String effectiveDate;

    /**
     * The constructor for a compensation object.
     */
    public Compensation() {
    }

    /**
     * Sets the employee for this compensation to the given employee.
     * 
     * @param employee: the employee to set compensation for
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Sets the compensation salary to the given value.
     * 
     * @param salary: the value to set the salary to
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Sets the date of effectiveness for this compensation to the given date.
     * 
     * @param date: the date to set the value to
     */
    public void setEffectiveDate(String date) {
        this.effectiveDate = date;
    }

    /**
     * Returns the employee whose compensation this represents.
     * 
     * @return Employee: the related employee
     */
    public Employee getEmployee() {
        return this.employee;
    }

    /**
     * Returns the salary value for this compensation.
     * 
     * @return int: the value of the salary
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     * Returns the date that this compensation takes effect.
     * 
     * @return String: the effective date for this compensation
     */
    public String getEffectiveDate() {
        return this.effectiveDate;
    }
}
