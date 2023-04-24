package com.mediscreen.DiabetesAssessment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ReportTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Report#Report()}
     *   <li>{@link Report#setAge(int)}
     *   <li>{@link Report#setAssessment(String)}
     *   <li>{@link Report#setPatient(Patient)}
     * </ul>
     */
    @Test
    void testConstructor() {
        Report actualReport = new Report();
        actualReport.setAge(1);
        actualReport.setAssessment("Assessment");
        Patient patient = new Patient(1, "Name", "Jane", "2020-03-01", "Gender", "42 Main St", "4105551212");

        actualReport.setPatient(patient);
        assertEquals(1, actualReport.getAge());
        assertEquals("Assessment", actualReport.getAssessment());
        assertSame(patient, actualReport.getPatient());
    }

}

