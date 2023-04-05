package com.MediScreen.MediScreen.Services.Implementation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import com.MediScreen.MediScreen.Repositories.PatientRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatientServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PatientServiceImplTest {
    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    /**
     * Method under test: {@link PatientServiceImpl#getPatientById(int)}
     */
    @Test
    void testGetPatientById() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        Optional<PatientDAO> ofResult = Optional.of(patientDAO);
        when(this.patientRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<PatientDAO> actualPatientById = this.patientServiceImpl.getPatientById(1);
        assertSame(ofResult, actualPatientById);
        assertTrue(actualPatientById.isPresent());
        verify(this.patientRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#getPatientById(int)}
     */
    @Test
    void testGetPatientById2() {
        when(this.patientRepository.findById((Integer) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.patientServiceImpl.getPatientById(1));
        verify(this.patientRepository).findById((Integer) any());
    }
}

