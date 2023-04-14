package com.mediscreen.DiabetesAssessment.service;

import com.mediscreen.DiabetesAssessment.model.History;
import com.mediscreen.DiabetesAssessment.model.Patient;
import com.mediscreen.DiabetesAssessment.model.Report;
import com.mediscreen.DiabetesAssessment.model.references.Triggers;
import com.mediscreen.DiabetesAssessment.proxies.HistoryProxy;
import com.mediscreen.DiabetesAssessment.proxies.PatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    PatientProxy patientProxy;
    @Autowired
    HistoryProxy historyProxy;
    @Autowired
    AssessAssignment assessAssignment;


    List<String> trigs = Arrays.asList(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Anormal",
            "Cholestérol",
            "Vertige",
            "Rechute",
            "Réaction",
            "Anticorps");


    public Report generateReportById(int patientId) {
        Report patientReportById = new Report();
        Patient patient = patientProxy.getPatientById(patientId);
        patientReportById.setPatient(patient);
        String dob = patient.getBirthDate();
        LocalDate formatter = LocalDate.parse(dob);
        LocalDate now = LocalDate.now();
        patientReportById.setAge(Period.between(formatter, now).getYears());
        List<History> histories = historyProxy.getHistoriesById(patient.getIdPatient());
        List<String> historyCommentaries = new ArrayList<>();
        Integer triggerCount = 0;
        for (History h : histories) {
            historyCommentaries.add(h.getCommentary());
        }
        List<String> commentariesToUpper = historyCommentaries.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> trigsToUpper = trigs.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> triggeredCommentaries = commentariesToUpper.stream().filter(trigsToUpper::contains).collect(Collectors.toList());
        for (String c : commentariesToUpper){
            for (int i = 0; i < trigs.size(); i++) {
                if (c.contains(trigsToUpper.get(i).toUpperCase(Locale.ROOT))) {
                    triggerCount++;
                }
            }
    }
            if(patientReportById.getAge() > 30){
                patientReportById.setAssessment(assessAssignment.assignAssessThirtyPlus(triggerCount).toString());
            }
            if (patientReportById.getPatient().getGender() == "male" || patientReportById.getPatient().getGender() == "female") {
                if (patientReportById.getPatient().getGender().equals("male")) {
                    if (patientReportById.getAge() < 30 || patientReportById.getPatient().equals(30)) {
                        patientReportById.setAssessment(assessAssignment.assignAssessMale(triggerCount).toString());
                    }
                } else if (patientReportById.getPatient().getGender().equals("female")) {
                    if (patientReportById.getAge() < 30 || patientReportById.getPatient().equals(30)) {
                        patientReportById.setAssessment(assessAssignment.assignAssessFemale(triggerCount).toString());
                    }
                }
            }
        return patientReportById;
    }

    public Report generateReportByPatientName(String name, String firstName){
        Report patientReportByPatientName = new Report();
        Patient patient = patientProxy.getPatientByName(name, firstName);
        patientReportByPatientName.setPatient(patient);
        String dob = patient.getBirthDate();
        LocalDate formatter = LocalDate.parse(dob);
        LocalDate now = LocalDate.now();
        patientReportByPatientName.setAge(Period.between(formatter, now).getYears());
        List<History> histories = historyProxy.getHistoriesById(patient.getIdPatient());
        Integer triggerCount = 0;
        for (History h : histories) {
            for (int i = 0; i < trigs.size(); i++) {
                if (h.getCommentary().contains(trigs.get(i))) {
                    triggerCount++;
                }
            }
        }
        if(patientReportByPatientName.getAge() > 30){
            patientReportByPatientName.setAssessment(assessAssignment.assignAssessThirtyPlus(triggerCount).toString());
        }
        if (patientReportByPatientName.getPatient().getGender() == "male" || patientReportByPatientName.getPatient().getGender() == "female") {
            if (patientReportByPatientName.getPatient().getGender().equals("male")) {
                if (patientReportByPatientName.getAge() < 30 || patientReportByPatientName.getPatient().equals(30)) {
                    patientReportByPatientName.setAssessment(assessAssignment.assignAssessMale(triggerCount).toString());
                }
            } else if (patientReportByPatientName.getPatient().getGender().equals("female")) {
                if (patientReportByPatientName.getAge() < 30 || patientReportByPatientName.getPatient().equals(30)) {
                    patientReportByPatientName.setAssessment(assessAssignment.assignAssessFemale(triggerCount).toString());
                }
            }
        }
        return patientReportByPatientName;
        }

    }
    /*
        Report patientReportByName = new Report();
        Patient patient = patientProxy.getPatientByName(firstName, name);
        patientReportByName.setPatient(patient);
        String dob = patient.getBirthDate();
        LocalDate formatter= LocalDate.parse(dob);
        LocalDate now = LocalDate.now();
        patientReportByName.setAge(Period.between(formatter,now).getYears());
        List<History> histories = historyProxy.getHistoriesById(patient.getName(), patient.getFirstName());
        Integer triggerCount = 0;
        for(History h : histories){
           // if(h.getCommentary().contains(triggers.toString())){
                triggerCount++;
            }
            if(patientReportByName.getPatient().getGender() == "male") {
                if (patientReportByName.getAge() < 30 || patientReportByName.getPatient().equals(30)) {
                    patientReportByName.setAssessment(assessAssignment.assessementsMale);
                }
            }else if(patientReportByName.getPatient().getGender() == "female"){
                if (patientReportByName.getAge() < 30 || patientReportByName.getPatient().equals(30)){
                    patientReportByName.setAssessment(assessAssignment.assessementsFemale);
                }
                else{
                    patientReportByName.setAssessment(assessAssignment.assessementsPlusThirty);
                }
            }
        return patientReportByName;
        */