package com.mediscreen.DiabetesAssessment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mediscreen.DiabetesAssessment.model.History;
import com.mediscreen.DiabetesAssessment.model.Patient;
import com.mediscreen.DiabetesAssessment.model.Report;
import com.mediscreen.DiabetesAssessment.proxies.HistoryProxy;
import com.mediscreen.DiabetesAssessment.proxies.PatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * List of triggers that are counted for assessements assignment
     */

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

    /**
     * Generates a report with id param
     * @param patientId
     * @return
     * @throws JsonProcessingException
     */

    public Report generateReportById(int patientId) throws JsonProcessingException {
        Report patientReportById = new Report();
        Patient patient = patientProxy.getPatientById(patientId);
        patientReportById.setPatient(patient);
        setPatientAge(patientReportById);
        List<History> histories = historyProxy.getHistoriesById(patient.getIdPatient());
        List<String> historyCommentaries = new ArrayList<>();
        Integer triggerCount = 0;
        histories.stream().forEach(history -> historyCommentaries.add(history.getCommentary()));
        List<String> trigsToUpper = trigs.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> commentariesToUpper = historyCommentaries.stream().map(String::toUpperCase).collect(Collectors.toList());
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

    /**
     * Generates a report with full name param
     * @param name
     * @param firstName
     * @return
     */
    public Report generateReportByPatientName(String name, String firstName){
        Report patientReportByPatientName = new Report();
        Patient patient = patientProxy.getPatientByName(name, firstName);
        patientReportByPatientName.setPatient(patient);
        setPatientAge(patientReportByPatientName);
        List<History> histories = historyProxy.getHistoriesById(patient.getIdPatient());
        List<String> historyCommentaries = new ArrayList<>();
        histories.stream().forEach(history -> historyCommentaries.add(history.getCommentary()));
        List<String> trigsToUpper = trigs.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> commentariesToUpper = historyCommentaries.stream().map(String::toUpperCase).collect(Collectors.toList());
        Integer triggerCount = 0;

        for (String c : commentariesToUpper) {
            for (int i = 0; i < trigs.size(); i++) {
                if (c.contains(trigsToUpper.get(i).toUpperCase(Locale.ROOT))){
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

        public Report setPatientAge(Report report){

            String birthdate = report.getPatient().getBirthDate();
            LocalDate formatter = LocalDate.parse(birthdate);
            LocalDate now = LocalDate.now();
            report.setAge(Period.between(formatter, now).getYears());

            return report;
        }
/*
        public Map<String, Long> triggers(List<String> commentaries , List<String> triggers){

        Map<String, Long> map = commentaries.stream().map(c -> c.split("\\b" + "\\s")).flatMap(w-> Arrays.stream(w).distinct()).filter(triggers::contains).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        return map;
        }
/*
        public Long countTriggers(List<String> commentaries) throws JsonProcessingException {
        List<String> trigsToUpper = trigs.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> commentariesToUpper = commentaries.stream().map(String::toUpperCase).collect(Collectors.toList());
        Map<String, List<String>> count1 = trigsToUpper.stream().flatMap(trigger -> trigsToUpper.stream().filter(commentariesToUpper::contains)).collect(Collectors.groupingBy(Function.identity()));
        long count = count1.size();
        return count;
        }

 */
    }
