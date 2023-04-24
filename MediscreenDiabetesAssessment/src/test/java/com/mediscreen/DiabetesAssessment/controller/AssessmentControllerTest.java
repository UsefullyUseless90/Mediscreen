package com.mediscreen.DiabetesAssessment.controller;

import com.mediscreen.DiabetesAssessment.model.Patient;
import com.mediscreen.DiabetesAssessment.model.Report;
import com.mediscreen.DiabetesAssessment.service.AssessmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AssessmentControllerTest {
    @Autowired
    private AssessmentController assessmentController;

    @MockBean
    private AssessmentService assessmentService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    /**
     * Method under test: {@link AssessmentController#getReportByPatientId(int)}
     */
    @Test
    void testGetReportByPatientId() throws Exception {
        Report report = new Report();
        report.setAge(15);
        report.setAssessment("Assessment");
        Patient patient = new Patient(1, "Name", "Jane", "2008-03-01", "Gender", "42 Main St", "4105551212");
        report.setPatient(patient);
        when(assessmentService.generateReportById(anyInt())).thenReturn(report);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/assess/id").param("patientId", "1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    /**
     * Method under test: {@link AssessmentController#getReportByPatientId(int)}
     */
    @Test
    void testGetReportByPatientIdWithError() throws Exception {
        Report report = new Report();
        report.setAge(15);
        report.setAssessment("Assessment");
        Patient patient = new Patient(2, "Name", "Jane", "2008-03-01", "Gender", "42 Main St", "4105551212");
        report.setPatient(patient);
        when(assessmentService.generateReportById(anyInt())).thenReturn(report);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/assess/id").param("patientid", "1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Method under test: {@link AssessmentController#getReportByPatientName(String, String)}
     */
    @Test
    void testGetReportByPatientName() throws Exception {
        Report report = new Report();
        report.setAge(15);
        report.setAssessment("Assessment");
        Patient patient = new Patient(1, "Name", "Jane", "2008-03-01", "Gender", "42 Main St", "4105551212");
        report.setPatient(patient);
        when(assessmentService.generateReportByPatientName((String) any(), (String) any())).thenReturn(report);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/assess/familyName")
                        .param("firstName", "Jane")
                        .param("name", "Name").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    /**
     * Method under test: {@link AssessmentController#getReportByPatientName(String, String)}
     */
    @Test
    void testGetReportByPatientNameWithError() throws Exception {
        Report report = new Report();
        report.setAge(15);
        report.setAssessment("Assessment");
        Patient patient = new Patient(1, "Name", "Jane", "2008-03-01", "Gender", "42 Main St", "4105551212");
        report.setPatient(patient);
        when(assessmentService.generateReportByPatientName((String) any(), (String) any())).thenReturn(report);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/assess/familyName")
                        .param("firstname", "")
                        .param("name", "").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


}

