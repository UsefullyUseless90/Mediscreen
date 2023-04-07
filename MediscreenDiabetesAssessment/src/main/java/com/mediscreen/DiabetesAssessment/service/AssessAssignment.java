package com.mediscreen.DiabetesAssessment.service;

import com.mediscreen.DiabetesAssessment.model.Report;
import com.mediscreen.DiabetesAssessment.model.references.Assessements;
import com.mediscreen.DiabetesAssessment.proxies.HistoryProxy;
import com.mediscreen.DiabetesAssessment.proxies.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AssessAssignment {

    @Autowired
    HistoryProxy historyProxy;
    @Autowired
    PatientProxy patientProxy;

    //Assessments
    Assessements assessementsMale;
    Assessements assessementsFemale;
    Assessements assessementsPlusThirty;

    public int counterAssess(){
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

    return 0;

    }


    public String assignAssessMale(int counter){
        Report report = new Report();
        if(counter == 0 ){
            report.setAssessment(assessementsMale.NONE.toString());
        }
        if(counter == 2){
            report.setAssessment(assessementsMale.BORDERLINE.toString());
        }
        if (counter == 3){
            report.setAssessment(assessementsMale.INDANGER.toString());
        }
        if (counter == 6){
            report.setAssessment(assessementsMale.EARLYONSET.toString());
        }
        return report.getAssessment();
    }

    public String assignAssessFemale(int counter){
        Report report = new Report();
        if(counter == 0 ){
            report.setAssessment(assessementsFemale.NONE.toString());
        }
        if(counter == 2){
            report.setAssessment(assessementsFemale.BORDERLINE.toString());
        }
        if (counter == 4){
            report.setAssessment(assessementsFemale.INDANGER.toString());
        }
        if (counter == 7){
            report.setAssessment(assessementsFemale.EARLYONSET.toString());
        }
        return report.getAssessment();
    }

    public String assignAssessThirtyPlus(int counter){

        Report report = new Report();
        if(counter >= 0 ){
            report.setAssessment(assessementsPlusThirty.NONE.toString());
        }
        if(counter >= 2){
            report.setAssessment(assessementsPlusThirty.BORDERLINE.toString());
        }
        if (counter >= 6){
            report.setAssessment(assessementsPlusThirty.INDANGER.toString());
        }
        if (counter >= 8){
            report.setAssessment(assessementsPlusThirty.EARLYONSET.toString());
        }
        return report.getAssessment();
    }

}
