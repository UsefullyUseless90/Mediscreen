package com.MediScreen.MediScreen.Services.Implementation;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import com.MediScreen.MediScreen.Models.DTO.PatientDTO;
import com.MediScreen.MediScreen.Repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Test
    void testGetAllPatients() {
        ArrayList<PatientDAO> patientDAOList = new ArrayList<>();

        PatientDAO actualPatientDAO = new PatientDAO(
                new PatientDTO("Given", "Family", "Dob", "Sex", "42 Main St", "4105551212"));
        PatientDAO actualPatientDAO1 = new PatientDAO(
                new PatientDTO("Given", "Family", "Dob", "Sex", "42 Main St", "4105551212"));
        PatientDAO actualPatientDAO2 = new PatientDAO(
                new PatientDTO("Given", "Family", "Dob", "Sex", "42 Main St", "4105551212"));

        patientDAOList.add(actualPatientDAO);
        patientDAOList.add(actualPatientDAO1);
        patientDAOList.add(actualPatientDAO2);

        when(patientServiceImpl.getAllPatients()).thenReturn(patientDAOList);
        Iterable<PatientDAO> actualAllPatients = patientServiceImpl.getAllPatients();
        assertThat(patientDAOList.size()).isEqualTo(3);
        assertThat(patientDAOList.contains(actualPatientDAO));
        assertThat(patientDAOList.contains(actualPatientDAO1));
        assertThat(patientDAOList.contains(actualPatientDAO2));
        assertSame(patientDAOList, actualAllPatients);
    }

    /**
     * Method under test: {@link PatientServiceImpl#getAllPatients()}
     */
    @Test
    void testGetAllPatientsEmptyList() {
        ArrayList<PatientDAO> patientDAOList = new ArrayList<>();
        when(patientRepository.findAll()).thenReturn(patientDAOList);
        Iterable<PatientDAO> actualAllPatients = patientServiceImpl.getAllPatients();
        assertSame(patientDAOList, actualAllPatients);
        assertTrue(((Collection<PatientDAO>) actualAllPatients).isEmpty());
        verify(patientRepository).findAll();
    }

    /**
     * Method under test: {@link PatientServiceImpl#getAllPatients()}
     */
    @Test
    void testGetAllPatientsWithException() {
        when(this.patientRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> patientServiceImpl.getAllPatients());
        verify(patientRepository).findAll();
    }


    @Test
    void testGetAllPatientsWithThrowException() {
        when(patientServiceImpl.getAllPatients()).thenThrow(new IllegalArgumentException("fsghfgh"));
        assertThrows(IllegalArgumentException.class, () -> patientServiceImpl.getAllPatients());
    }

    /**
     * Method under test: {@link PatientServiceImpl#getPatientByName(String)}
     */
    @Test
    void testGetPatientByNameEmptyList() {
        ArrayList<PatientDAO> patientDAOList = new ArrayList<>();
        when(patientRepository.findByName((String) any())).thenReturn(patientDAOList);
        List<PatientDAO> actualPatientByName = patientServiceImpl.getPatientByName("Name");
        assertSame(patientDAOList, actualPatientByName);
        assertTrue(actualPatientByName.isEmpty());
        verify(patientRepository).findByName((String) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#getPatientByName(String)}
     */
    @Test
    void testGetPatientByNameWithException() {
        when(patientRepository.findByName((String) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> patientServiceImpl.getPatientByName("Name"));
        verify(patientRepository).findByName((String) any());
    }


    @Test
    void testGetPatientByNameWithActualPatients() {
        List<PatientDAO> patients = new ArrayList<>();
        PatientDAO patientDAO = new PatientDAO();
        PatientDAO patientDAO1 = new PatientDAO();
        PatientDAO patientDAO2 = new PatientDAO();
        patients.add(patientDAO);
        patients.add(patientDAO1);
        patients.add(patientDAO2);
        when(patientRepository.findByName(anyString())).thenReturn(patients);
        List<PatientDAO> actualPatientByName = patientRepository.findByName("Name");
        assertSame(patients, actualPatientByName);
        assertTrue(actualPatientByName.contains(patientDAO));
        assertThat(patients.size()).isEqualTo(3);
        assertThat(patients.contains(patientDAO));
        assertThat(patients.contains(patientDAO1));
        assertThat(patients.contains(patientDAO1));
    }


    @Test
    void testGetPatientByFullName() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");

        when(patientRepository.findByNameAndFirstName(anyString(), anyString())).thenReturn(patientDAO);
        assertSame(patientDAO, patientRepository.findByNameAndFirstName("Name", "Jane"));
        assertThat(patientDAO.getIdPatient()).isEqualTo(1);
        assertThat(patientDAO.getBirthDate()).isEqualTo("2020-03-01");
        assertThat(patientDAO.getFirstName()).isEqualTo("Jane");
        assertThat(patientDAO.getName()).isEqualTo("Name");
        assertThat(patientDAO.getPhoneNumber()).isEqualTo("4105551212");
        assertThat(patientDAO.getPostalAddress()).isEqualTo("42 Main St");
        assertThat(patientDAO.getGender()).isEqualTo("Gender");
    }


    @Test
    void testGetPatientByFullNameWithThrowException() {
        when(patientServiceImpl.getPatientByFullName(anyString(), anyString()))
                .thenThrow(new IllegalArgumentException("gfhdfghdf"));
        assertThrows(IllegalArgumentException.class, () -> this.patientServiceImpl.getPatientByFullName("Name", "Jane"));
    }


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
        when(patientServiceImpl.getPatientById(anyInt())).thenReturn(ofResult);
        Optional<PatientDAO> actualPatientById = patientServiceImpl.getPatientById(patientDAO.getIdPatient());
        assertSame(ofResult, actualPatientById);
        assertTrue(actualPatientById.isPresent());
        assertThat(patientServiceImpl.getPatientById(anyInt())).isEqualTo(Optional.of(patientDAO));
    }

    @Test
    void testGetPatientByIdExceptionWithThrowException() {
        when(patientServiceImpl.getPatientById((anyInt()))).thenThrow(new IllegalArgumentException("gdfgdsfgsd"));
        assertThrows(IllegalArgumentException.class, () -> patientServiceImpl.getPatientById(1));
    }

    /**
     * Method under test: {@link PatientServiceImpl#savePatient(PatientDAO)}
     */
    @Test
    void testSavePatientFemaleLetter() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("F");
        patientServiceImpl.setGenderParamFromLetter(patientDAO);
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        when(this.patientRepository.save((PatientDAO) any())).thenReturn(patientDAO);

        PatientDAO patientDAO1 = new PatientDAO();
        patientDAO1.setBirthDate("2020-03-01");
        patientDAO1.setFirstName("Jane");
        patientDAO1.setGender("F");
        patientServiceImpl.setGenderParamFromLetter(patientDAO1);
        patientDAO1.setIdPatient(1);
        patientDAO1.setName("Name");
        patientDAO1.setPhoneNumber("4105551212");
        patientDAO1.setPostalAddress("42 Main St");
        PatientDAO actualSavePatientResult = this.patientServiceImpl.savePatient(patientDAO1);
        assertSame(patientDAO1, actualSavePatientResult);
        assertEquals("female", actualSavePatientResult.getGender());
        verify(this.patientRepository).save((PatientDAO) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#savePatient(PatientDAO)}
     */

    @Test
    void testSavePatientMenLetter() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("John");
        patientDAO.setGender("M");
        patientServiceImpl.setGenderParamFromLetter(patientDAO);
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        when(patientRepository.save(patientDAO)).thenReturn(patientDAO);
        PatientDAO patient = patientRepository.save(patientDAO);
        assertNotNull(patient);
        assertThat(patient.getBirthDate().equals("2020-03-01"));
        assertThat(patient.getFirstName().equals("John"));
        assertThat(patient.getFirstName().equals("Name"));
        assertThat(patient.getGender().equals("male"));
        assertEquals(patient.getIdPatient(), 1);
        assertThat(patient.getPhoneNumber().equals("4105551212"));
        assertThat(patient.getPostalAddress().equals("42 Main St"));

    }


    /**
     * Method under test: {@link PatientServiceImpl#updatePatient(PatientDAO)}
     */
    @Test
    void testUpdatePatientWithException() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");

        PatientDAO patientDAO1 = new PatientDAO();
        patientDAO1.setBirthDate("2020-03-01");
        patientDAO1.setFirstName("Jane");
        patientDAO1.setGender("Gender");
        assertThrows(IllegalArgumentException.class, () -> patientServiceImpl.setGenderParamFromLetter(patientDAO));
        patientDAO1.setIdPatient(1);
        patientDAO1.setName("Name");
        patientDAO1.setPhoneNumber("4105551212");
        patientDAO1.setPostalAddress("42 Main St");
        when(this.patientRepository.save((PatientDAO) any())).thenReturn(patientDAO1);
        when(this.patientRepository.findByIdPatient(org.mockito.Mockito.anyInt())).thenReturn(patientDAO);

        PatientDAO patientDAO2 = new PatientDAO();
        patientDAO2.setBirthDate("2020-03-01");
        patientDAO2.setFirstName("Jane");
        patientDAO2.setIdPatient(1);
        patientDAO2.setName("Name");
        patientDAO2.setPhoneNumber("4105551212");
        patientDAO2.setPostalAddress("42 Main St");
        PatientDAO actualUpdatePatientResult = this.patientServiceImpl.updatePatient(patientDAO);
        assertSame(patientDAO, actualUpdatePatientResult);
        assertEquals("2020-03-01", actualUpdatePatientResult.getBirthDate());
        assertEquals("42 Main St", actualUpdatePatientResult.getPostalAddress());
        assertEquals("4105551212", actualUpdatePatientResult.getPhoneNumber());
        assertEquals("Name", actualUpdatePatientResult.getName());
        assertEquals("Jane", actualUpdatePatientResult.getFirstName());
        verify(this.patientRepository).findByIdPatient(org.mockito.Mockito.anyInt());
        verify(this.patientRepository).save((PatientDAO) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#updatePatient(PatientDAO)}
     */
    @Test
    void testUpdatePatientGenderLetter() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("M");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        when(this.patientRepository.save((PatientDAO) any())).thenThrow(new IllegalArgumentException("F"));
        when(this.patientRepository.findByIdPatient(org.mockito.Mockito.anyInt())).thenReturn(patientDAO);
        PatientDAO patientDAO1 = new PatientDAO();
        patientDAO1.setBirthDate("2020-03-01");
        patientDAO1.setFirstName("Jane");
        patientDAO1.setGender("F");
        patientServiceImpl.setGenderParamFromLetter(patientDAO1);
        patientDAO1.setIdPatient(1);
        patientDAO1.setName("Name");
        patientDAO1.setPhoneNumber("4105551212");
        patientDAO1.setPostalAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> this.patientServiceImpl.updatePatient(patientDAO1));
        assertThat(patientDAO1.getGender().equals("female"));
        verify(this.patientRepository).findByIdPatient(org.mockito.Mockito.anyInt());
        verify(this.patientRepository).save((PatientDAO) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#setGenderParamFromLetter(PatientDAO)}
     */
    @Test
    void testSetGenderParamFromLetterWithError() {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> this.patientServiceImpl.setGenderParamFromLetter(patientDAO));
    }

    /**
     * Method under test: {@link PatientServiceImpl#setGenderParamFromLetter(PatientDAO)}
     */
    @Test
    void testSetGenderParamFromLetterWithException() {
        PatientDAO patientDAO = mock(PatientDAO.class);
        when(patientDAO.getGender()).thenReturn("Gender");
        doNothing().when(patientDAO).setBirthDate((String) any());
        doNothing().when(patientDAO).setFirstName((String) any());
        doNothing().when(patientDAO).setGender((String) any());
        doNothing().when(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        doNothing().when(patientDAO).setName((String) any());
        doNothing().when(patientDAO).setPhoneNumber((String) any());
        doNothing().when(patientDAO).setPostalAddress((String) any());
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        assertThrows(IllegalArgumentException.class, () -> patientServiceImpl.setGenderParamFromLetter(patientDAO));
        verify(patientDAO, atLeast(1)).getGender();
        verify(patientDAO).setBirthDate((String) any());
        verify(patientDAO).setFirstName((String) any());
        verify(patientDAO).setGender((String) any());
        verify(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        verify(patientDAO).setName((String) any());
        verify(patientDAO).setPhoneNumber((String) any());
        verify(patientDAO).setPostalAddress((String) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#setGenderParamFromLetter(PatientDAO)}
     */
    @Test
    void testSetGenderParamFromLetterMenLetter() {
        PatientDAO patientDAO = mock(PatientDAO.class);
        when(patientDAO.getGender()).thenReturn("M");
        doNothing().when(patientDAO).setBirthDate((String) any());
        doNothing().when(patientDAO).setFirstName((String) any());
        doNothing().when(patientDAO).setGender((String) any());
        doNothing().when(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        doNothing().when(patientDAO).setName((String) any());
        doNothing().when(patientDAO).setPhoneNumber((String) any());
        doNothing().when(patientDAO).setPostalAddress((String) any());
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        patientServiceImpl.setGenderParamFromLetter(patientDAO);
        verify(patientDAO).getGender();
        verify(patientDAO).setBirthDate((String) any());
        verify(patientDAO).setFirstName((String) any());
        verify(patientDAO, atLeast(1)).setGender((String) any());
        verify(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        verify(patientDAO).setName((String) any());
        verify(patientDAO).setPhoneNumber((String) any());
        verify(patientDAO).setPostalAddress((String) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#setGenderParamFromLetter(PatientDAO)}
     */
    @Test
    void testSetGenderParamFromLetterMen() {
        PatientDAO patientDAO = mock(PatientDAO.class);
        when(patientDAO.getGender()).thenReturn("Men");
        doNothing().when(patientDAO).setBirthDate((String) any());
        doNothing().when(patientDAO).setFirstName((String) any());
        doNothing().when(patientDAO).setGender((String) any());
        doNothing().when(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        doNothing().when(patientDAO).setName((String) any());
        doNothing().when(patientDAO).setPhoneNumber((String) any());
        doNothing().when(patientDAO).setPostalAddress((String) any());
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        patientServiceImpl.setGenderParamFromLetter(patientDAO);
        verify(patientDAO, atLeast(1)).getGender();
        verify(patientDAO).setBirthDate((String) any());
        verify(patientDAO).setFirstName((String) any());
        verify(patientDAO, atLeast(1)).setGender((String) any());
        verify(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        verify(patientDAO).setName((String) any());
        verify(patientDAO).setPhoneNumber((String) any());
        verify(patientDAO).setPostalAddress((String) any());
    }

    /**
     * Method under test: {@link PatientServiceImpl#setGenderParamFromLetter(PatientDAO)}
     */
    @Test
    void testSetGenderParamFromLetterFemaleLetter() {
        PatientDAO patientDAO = mock(PatientDAO.class);
        when(patientDAO.getGender()).thenReturn("F");
        doNothing().when(patientDAO).setBirthDate((String) any());
        doNothing().when(patientDAO).setFirstName((String) any());
        doNothing().when(patientDAO).setGender((String) any());
        doNothing().when(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        doNothing().when(patientDAO).setName((String) any());
        doNothing().when(patientDAO).setPhoneNumber((String) any());
        doNothing().when(patientDAO).setPostalAddress((String) any());
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        patientServiceImpl.setGenderParamFromLetter(patientDAO);
        verify(patientDAO, atLeast(1)).getGender();
        verify(patientDAO).setBirthDate((String) any());
        verify(patientDAO).setFirstName((String) any());
        verify(patientDAO, atLeast(1)).setGender((String) any());
        verify(patientDAO).setIdPatient(org.mockito.Mockito.anyInt());
        verify(patientDAO).setName((String) any());
        verify(patientDAO).setPhoneNumber((String) any());
        verify(patientDAO).setPostalAddress((String) any());
    }

}

