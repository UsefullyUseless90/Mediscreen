package com.MediScreen.MediScreen.Models.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.MediScreen.MediScreen.Models.DTO.PatientDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PatientDAOTest {

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    void testConstructor() {
        PatientDAO actualPatientDAO = new PatientDAO(
                new PatientDTO("Given", "Family", "Dob", "Sex", "42 Main St", "4105551212"));
        assertEquals("Dob", actualPatientDAO.getBirthDate());
        assertEquals("42 Main St", actualPatientDAO.getPostalAddress());
        assertEquals("4105551212", actualPatientDAO.getPhoneNumber());
        assertEquals("Family", actualPatientDAO.getName());
        assertEquals("Sex", actualPatientDAO.getGender());
        assertEquals("Given", actualPatientDAO.getFirstName());
    }
}
