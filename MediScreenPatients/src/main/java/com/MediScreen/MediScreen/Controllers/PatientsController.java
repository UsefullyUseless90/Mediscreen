package com.MediScreen.MediScreen.Controllers;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import com.MediScreen.MediScreen.Models.DTO.PatientDTO;
import com.MediScreen.MediScreen.Repositories.PatientRepository;
import com.MediScreen.MediScreen.Services.IPatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientsController {

    @Autowired
    IPatientService iPatientService;
    @Autowired
    PatientRepository patientRepository;

    Logger log = LogManager.getLogger(PatientsController.class);

    /**
     * endpoint to get all patients
     * @return a list of patients
     */
    @RequestMapping("/allPatients")
    public ResponseEntity<Iterable<PatientDAO>> getAllPatients(){
        Iterable<PatientDAO> patients = iPatientService.getAllPatients();
        ResponseEntity<Iterable<PatientDAO>> listePatient = ResponseEntity.status(HttpStatus.OK).body(patients);
        log.info("this is all the patient registered");
        return listePatient;
    }

    /**
     * endpoint to get all patients with a name
     * @param name
     * @return a list of patients
     */
    @RequestMapping(value = "/patientName", params = "name")
    public ResponseEntity<List<PatientDAO>> getPatientByName(@RequestParam String name){
        List<PatientDAO> patientList= iPatientService.getPatientByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(patientList);
    }

    /**
     * endpoint to get patient with his full name
     * @param name
     * @param firstName
     * @return a patient
     */
    @RequestMapping(value = "/patientName&firstName", params = {"name", "firstName"})
    public ResponseEntity<PatientDAO> getPatientByFullName(@RequestParam String name,
                                                             @RequestParam String firstName){
        PatientDAO patient= iPatientService.getPatientByFullName(name, firstName);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    /**
     * endpoint to get patient with his id
     * @param id
     * @return a patient
     */
    @RequestMapping(value="/patientId", params = "id")
    public ResponseEntity<Optional<PatientDAO>> getPatientById(@RequestParam int id){
        Optional<PatientDAO> patientDAO = iPatientService.getPatientById(id);
        log.info("this is the the patient with the following id: " + patientDAO);
        return ResponseEntity.status(HttpStatus.OK).body(patientDAO);
    }

    /**
     * endpoint that used for adding new patient
     * @param patientDTO
     * @return the new patient
     */
    @PostMapping(path = "/add")
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO patientDTO){
        PatientDAO patientToSave = new PatientDAO(patientDTO);
        iPatientService.savePatient(patientToSave);
        ResponseEntity<?> patientSaved = ResponseEntity.status(HttpStatus.OK).body(patientToSave);
        log.info("New patient added: " + patientSaved);
        return patientSaved;
    }

    /**
     * endpoint that used for updating an existing patient
     * @param patientDAO
     * @return the updated patient
     */
    @PutMapping
    @RequestMapping(value="/edit")
    public PatientDAO updatePatient(@RequestBody PatientDAO patientDAO){
        PatientDAO patientUpdated = iPatientService.updatePatient(patientDAO);
        log.info("Patient infos updated!" + patientUpdated);
        return patientUpdated;
    }
}
