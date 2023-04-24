package com.MediScreen.MediScreen.Models.DTO;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class PatientDTOTest {
    /**
     * Method under test: {@link PatientDTO#PatientDTO()}
     */
    @Test
    void testConstructor() {
        PatientDTO actualPatientDTO = new PatientDTO();
        assertNull(actualPatientDTO.getAddress());
        assertNull(actualPatientDTO.getSex());
        assertNull(actualPatientDTO.getPhone());
        assertNull(actualPatientDTO.getGiven());
        assertNull(actualPatientDTO.getFamily());
        assertNull(actualPatientDTO.getDob());
    }
}

