package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
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
 * Test for the ReportingStructureService implementation.
 * Accesses the REST endpoint to create a ReportingStructure for John Lennon and verifies that the reporting data is correct.
 * 
 * @author Thomas Fuller
 */
public class ReportingStructureServiceImplTest {

    private String reportingStructureUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    /**
     * Test for the ReportingStructure creation endpoint.
     */
    public void testCreate() {
        // ID for John Lennon
        String id = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ReportingStructure reportStructure = new ReportingStructure();

        // Create checks
        ReportingStructure createdReport = restTemplate.postForEntity(reportingStructureUrl, reportStructure, ReportingStructure.class, id).getBody();

        assertNotNull(createdReport.getEmployee());
        assertEquals("John", createdReport.getEmployee().getFirstName());
        assertEquals(4, createdReport.getNumberOfReports());
    }
}