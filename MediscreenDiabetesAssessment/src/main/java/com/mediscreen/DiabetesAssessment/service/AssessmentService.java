package com.mediscreen.DiabetesAssessment.service;

import com.mediscreen.DiabetesAssessment.model.Report;

public interface AssessmentService {

    Report generateReportById(int patientId);
    Report generateReportByPatientName(String firstName, String name);
}
