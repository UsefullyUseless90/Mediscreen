package com.MediScreen.MediScreen.Controllers;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import com.MediScreen.MediScreen.Repositories.PatientRepository;
import com.MediScreen.MediScreen.Services.IPatientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientsController {

    @Autowired
    IPatientService iPatientService;
    @Autowired
    PatientRepository patientRepository;

    Logger log = LogManager.getLogger(PatientsController.class);

    @RequestMapping("/allPatients")
    public ResponseEntity<Iterable<PatientDAO>> getAllPatients(){
        Iterable<PatientDAO> patients = iPatientService.getAllPatients();
        ResponseEntity<Iterable<PatientDAO>> listePatient = ResponseEntity.status(HttpStatus.OK).body(patients);
        log.info("this is all the patient registered");
        return listePatient;
    }
    @RequestMapping(value = "/patientName", params = {"name", "firstName"})
    public ResponseEntity<List<PatientDAO>> getPatientByName(@RequestParam String name,
                                                             @RequestParam String firstName){
        List<PatientDAO> patientList= iPatientService.getPatientByName(name, firstName);
        return ResponseEntity.status(HttpStatus.OK).body(patientList);

    }

    @RequestMapping(value="/patientId", params = "id")
    public ResponseEntity<Optional<PatientDAO>> getPatientById(@RequestParam int id){
        Optional<PatientDAO> patientDAO = iPatientService.getPatientById(id);
        log.info("this is the the patient with the following id: " + patientDAO);
        return ResponseEntity.status(HttpStatus.OK).body(patientDAO);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> savePatient(@RequestParam String family, @RequestParam String given, @RequestParam String dob,
                                         @RequestParam String sex, @RequestParam String address, @RequestParam String phone){
        PatientDAO patientDAO = new PatientDAO(new Random().nextInt(), family,given,dob,sex,address,phone);
        iPatientService.savePatient(patientDAO);
        ResponseEntity<?> patientSaved = ResponseEntity.status(HttpStatus.OK).body(patientDAO);
        log.info("New patient added: " + patientSaved);
        return patientSaved;
    }
    @PutMapping
    @RequestMapping(value="/edit")
    public PatientDAO updatePatient(@RequestBody PatientDAO patientDAO){
        Optional<PatientDAO> patientDAO1 = patientRepository.findById(patientDAO.getIdPatient());
        PatientDAO patientUpdated = iPatientService.updatePatient(patientDAO);
        log.info("Patient infos updated!" + patientUpdated);
       // ResponseEntity<PatientDAO> patientDAOResponseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(patientUpdated);
        return patientUpdated;
    }
}
