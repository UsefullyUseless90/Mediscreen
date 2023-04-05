package com.MediScreen.MediScreen.Services.Implementation;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import com.MediScreen.MediScreen.Repositories.PatientRepository;
import com.MediScreen.MediScreen.Services.IPatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class PatientServiceImpl implements IPatientService {

    @Autowired
    PatientRepository patientRepository;

    /**
     *
     * @return
     */
    public Iterable<PatientDAO> getAllPatients() {
        Iterable<PatientDAO> patients = patientRepository.findAll();
        return patients;
    }

    /**
     *
     * @param name
     * @param firstName
     * @return
     */
    public List<PatientDAO> getPatientByName(String name, String firstName) {
       return patientRepository.findByNameOrFirstName(name, firstName);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<PatientDAO> getPatientById(int id){
        return patientRepository.findById(id);
    }

    /**
     *
     * @param patientDAO
     * @return
     */
    @Transactional
    public PatientDAO savePatient(PatientDAO patientDAO){
        patientDAO.setIdPatient(new Random().nextInt());
        if(patientDAO.getGender().equals("F") || patientDAO.getGender().equals("M")){
            setGenderParamFromLetter(patientDAO);
        }
        else{
            patientDAO.setGender(patientDAO.getGender());
        }
        patientRepository.save(patientDAO);
        return patientDAO;
    }

    /**
     *
     * @param patientDAO
     * @return
     */
    @Transactional
    public PatientDAO updatePatient(PatientDAO patientDAO){
        PatientDAO patientUpdate = patientRepository.findByIdPatient(patientDAO.getIdPatient());
        patientUpdate.setFirstName(patientDAO.getFirstName());
        patientUpdate.setName(patientDAO.getName());
        patientUpdate.setName(patientDAO.getName());
        patientUpdate.setBirthDate(patientDAO.getBirthDate());
        patientUpdate.setPostalAddress(patientDAO.getPostalAddress());
        patientUpdate.setPhoneNumber(patientDAO.getPhoneNumber());
        if(patientDAO.getGender().equals("F") || patientDAO.getGender().equals("M")){
            setGenderParamFromLetter(patientDAO);
        }
        else{
            patientUpdate.setGender(patientDAO.getGender());
        }
        patientRepository.save(patientUpdate);
        return patientUpdate;
    }

    public void setGenderParamFromLetter(PatientDAO patientDAO){
        if (patientDAO.getGender().equals("M") || patientDAO.getGender().equals("Men"))  {
            patientDAO.setGender("male");
        } else if (patientDAO.getGender().equals("F") || patientDAO.getGender().equals("Female")) {
            patientDAO.setGender("female");
        } else {
            throw new IllegalArgumentException("Please answer a valid gender");
        }
    }


}
