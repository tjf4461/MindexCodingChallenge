package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/**
 * Test for the CompensationService implementation.
 * Accesses the REST endpoint to create a compensation entry for John Lennon and verifies that the endpoint can also read with ids.
 * 
 * @author Thomas Fuller
 */
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;
    private String employeeIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    }

    @Test
    /**
     * Test for the Compensation creation and reading endpoints.
     */
    public void testCreateRead() {
        String id = "16a596ae-edd3-4847-99fe-c4518e82c86f";

        // Test assumes that the employee reading endpoint is fully functional
        Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, id).getBody();


        Compensation compensation = new Compensation();
        compensation.setEmployee(readEmployee);
        compensation.setSalary(100000);
        compensation.setEffectiveDate("01/01/2025");

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, compensation, Compensation.class).getBody();

        assertNotNull(createdCompensation.getEmployee());
        assertCompensationEquivalence(compensation, createdCompensation);


        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, id).getBody();
        assertCompensationEquivalence(readCompensation, createdCompensation);
    }
      
    /**
     * Test to compare two Employee objects. Verifies that the state of both are identical.
     *
     * @param expected: the controlled Employee object
     * @param actual: the received Employee object
     */
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

    /**
     * Test to compare two Compensation objects. Verifies that the state of both are identical.
     * 
     * @param expected: the controlled Compensation object
     * @param actual: the received Compensation object
     */
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEmployeeEquivalence(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
