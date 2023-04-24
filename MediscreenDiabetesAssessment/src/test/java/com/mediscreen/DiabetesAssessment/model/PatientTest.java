package com.mediscreen.DiabetesAssessment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatientTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Patient#Patient()}
     *   <li>{@link Patient#setBirthDate(String)}
     *   <li>{@link Patient#setFirstName(String)}
     *   <li>{@link Patient#setGender(String)}
     *   <li>{@link Patient#setIdPatient(int)}
     *   <li>{@link Patient#setName(String)}
     *   <li>{@link Patient#setPhoneNumber(String)}
     *   <li>{@link Patient#setPostalAddress(String)}
     * </ul>
     */
    @Test
    void testConstructor() {
        Patient actualPatient = new Patient();
        actualPatient.setBirthDate("2020-03-01");
        actualPatient.setFirstName("Jane");
        actualPatient.setGender("Gender");
        actualPatient.setIdPatient(1);
        actualPatient.setName("Name");
        actualPatient.setPhoneNumber("4105551212");
        actualPatient.setPostalAddress("42 Main St");
        assertEquals("2020-03-01", actualPatient.getBirthDate());
        assertEquals("Jane", actualPatient.getFirstName());
        assertEquals("Gender", actualPatient.getGender());
        assertEquals(1, actualPatient.getIdPatient());
        assertEquals("Name", actualPatient.getName());
        assertEquals("4105551212", actualPatient.getPhoneNumber());
        assertEquals("42 Main St", actualPatient.getPostalAddress());
    }

}

