package com.mediscreen.DiabetesAssessment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mediscreen.DiabetesAssessment.model.Report;
import com.mediscreen.DiabetesAssessment.service.AssessmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RestController
@RequestMapping("/assess")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    Logger log = LogManager.getLogger(AssessmentController.class);

    /**
     * endpoint with id params
     * @param patientId
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/id", params = "patientId")
    public Report getReportByPatientId(@RequestParam int patientId) throws JsonProcessingException {
        log.info("report generated");
        return assessmentService.generateReportById(patientId);
    }

    /**
     * endpoint with full name params
     * @param name
     * @param firstName
     * @return
     */
    @RequestMapping(value = "/familyName", params = {"firstName", "name"})
    public Report getReportByPatientName(@RequestParam String name, @RequestParam String firstName){
        log.info("report generated");
        return assessmentService.generateReportByPatientName(name,firstName);
    }
}
