package com.MediScreen.MediScreen.Services;


import com.MediScreen.MediScreen.Models.DAO.PatientDAO;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    Iterable<PatientDAO> getAllPatients();
    List<PatientDAO> getPatientByName(String name, String firstName);
    Optional<PatientDAO> getPatientById(int id);
    PatientDAO savePatient(PatientDAO patientDAO);
    PatientDAO updatePatient(PatientDAO patientDAO);
}
