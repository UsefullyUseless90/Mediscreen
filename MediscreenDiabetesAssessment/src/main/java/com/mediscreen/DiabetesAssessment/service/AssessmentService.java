package com.mediscreen.DiabetesAssessment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mediscreen.DiabetesAssessment.model.Report;

public interface AssessmentService {

    Report generateReportById(int patientId) throws JsonProcessingException;
    Report generateReportByPatientName(String firstName, String name);
}
