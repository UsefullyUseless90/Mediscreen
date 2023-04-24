package com.mediscreen.DiabetesAssessment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mediscreen.DiabetesAssessment.model.History;
import com.mediscreen.DiabetesAssessment.model.Patient;
import com.mediscreen.DiabetesAssessment.model.Report;
import com.mediscreen.DiabetesAssessment.proxies.HistoryProxy;
import com.mediscreen.DiabetesAssessment.proxies.PatientProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AssessmentServiceImpl.class})
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AssessmentServiceImplTest {

    @MockBean
    private AssessAssignment assessAssignment;

    @InjectMocks
    private AssessmentServiceImpl assessmentServiceImpl;

    @MockBean
    private HistoryProxy historyProxy;

    @MockBean
    private PatientProxy patientProxy;



    @Test
    void generateReportByIdTestThirtyPlus() throws JsonProcessingException {
        Patient patient = new Patient();
        patient.setIdPatient(1);
        patient.setGender("female");
        patient.setFirstName("Jane");
        patient.setName("Doe");
        patient.setBirthDate("1985-01-01");

        History history = new History();
        history.setHistoryId(1);
        history.setCommentary("Hémoglobine A1C");

        List<History> histories = new ArrayList<>();
        histories.add(history);

        Report report1 = new Report();

        when(patientProxy.getPatientById(1)).thenReturn(patient);
        when(historyProxy.getHistoriesById(1)).thenReturn(histories);
        when(assessAssignment.assignAssessThirtyPlus(1)).thenReturn("IN DANGER");

        Report report = assessmentServiceImpl.generateReportById(1);

        Assertions.assertEquals(patient, report.getPatient());
        Assertions.assertEquals("IN DANGER", report.getAssessment());
    }

    /**
     *
     * @throws JsonProcessingException
     */
    @Test
    void generateReportByIdTestFemale() throws JsonProcessingException {
        Patient patient = new Patient();
        patient.setIdPatient(1);
        patient.setGender("female");
        patient.setFirstName("Jane");
        patient.setName("Doe");
        patient.setBirthDate("2005-01-01");

        History history = new History();
        history.setHistoryId(1);
        history.setCommentary("Hémoglobine A1C");

        List<History> histories = new ArrayList<>();
        histories.add(history);

        when(patientProxy.getPatientById(1)).thenReturn(patient);
        when(historyProxy.getHistoriesById(1)).thenReturn(histories);
        when(assessAssignment.assignAssessFemale(1)).thenReturn("IN DANGER");

        Report report = assessmentServiceImpl.generateReportById(1);

        Assertions.assertEquals(patient, report.getPatient());
        Assertions.assertEquals("IN DANGER", report.getAssessment());
    }
    @Test
    void generateReportByIdTestMale() throws JsonProcessingException {
        Patient patient = new Patient();
        patient.setIdPatient(1);
        patient.setGender("male");
        patient.setFirstName("John");
        patient.setName("Doe");
        patient.setBirthDate("2005-01-01");

        History history = new History();
        history.setHistoryId(1);
        history.setCommentary("Hémoglobine A1C");

        List<History> histories = new ArrayList<>();
        histories.add(history);

        Report report1 = new Report();

        when(patientProxy.getPatientById(1)).thenReturn(patient);
        when(historyProxy.getHistoriesById(1)).thenReturn(histories);
        when(assessAssignment.assignAssessMale(1)).thenReturn("IN DANGER");

        Report report = assessmentServiceImpl.generateReportById(1);

        Assertions.assertEquals(patient, report.getPatient());
        Assertions.assertEquals("IN DANGER", report.getAssessment());
    }

    @Test
    void generateReportByPatientNameTestThirtyPlus() throws JsonProcessingException {
        Patient patient = new Patient();
        patient.setIdPatient(1);
        patient.setGender("female");
        patient.setFirstName("Jane");
        patient.setName("Doe");
        patient.setBirthDate("1985-01-01");

        History history = new History();
        history.setHistoryId(1);
        history.setCommentary("Anormal");

        List<History> histories = new ArrayList<>();
        histories.add(history);

        when(patientProxy.getPatientByName("Doe", "Jane")).thenReturn(patient);
        when(historyProxy.getHistoriesById(1)).thenReturn(histories);
        when(assessAssignment.assignAssessThirtyPlus(1)).thenReturn("IN DANGER");

        Report report = assessmentServiceImpl.generateReportByPatientName("Doe", "Jane");

        Assertions.assertEquals(patient, report.getPatient());
        Assertions.assertEquals("IN DANGER", report.getAssessment());
    }
    @Test
    void generateReportByPatientNameTestFemale() throws JsonProcessingException {
        Patient patient = new Patient();
        patient.setIdPatient(1);
        patient.setGender("female");
        patient.setFirstName("Jane");
        patient.setName("Doe");
        patient.setBirthDate("2005-01-01");

        History history = new History();
        history.setHistoryId(1);
        history.setCommentary("Anormal");

        List<History> histories = new ArrayList<>();
        histories.add(history);

        when(patientProxy.getPatientByName("Doe", "Jane")).thenReturn(patient);
        when(historyProxy.getHistoriesById(1)).thenReturn(histories);
        when(assessAssignment.assignAssessFemale(1)).thenReturn("IN DANGER");

        Report report = assessmentServiceImpl.generateReportByPatientName("Doe", "Jane");

        Assertions.assertEquals(patient, report.getPatient());
        Assertions.assertEquals("IN DANGER", report.getAssessment());
    }
    @Test
    void generateReportByPatientNameTestMale() throws JsonProcessingException {
        Patient patient = new Patient();
        patient.setIdPatient(1);
        patient.setGender("male");
        patient.setFirstName("John");
        patient.setName("Doe");
        patient.setBirthDate("2005-01-01");

        History history = new History();
        history.setHistoryId(1);
        history.setCommentary("Anormal");

        List<History> histories = new ArrayList<>();
        histories.add(history);

        when(patientProxy.getPatientByName("Doe", "John")).thenReturn(patient);
        when(historyProxy.getHistoriesById(1)).thenReturn(histories);
        when(assessAssignment.assignAssessMale(1)).thenReturn("IN DANGER");

        Report report = assessmentServiceImpl.generateReportByPatientName("Doe", "John");

        Assertions.assertEquals(patient, report.getPatient());
        Assertions.assertEquals("IN DANGER", report.getAssessment());
    }
}


