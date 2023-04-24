package com.MediScreen.MediScreen.Repositories;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientDAO, Integer> {

    /**
     * Request to DB with patient's full name param
     * @param name
     * @param firstName
     * @return a patient
     */
    PatientDAO findByNameAndFirstName(String name, String firstName);

    /**
     * Request to DB with patient's name param
     * @param name
     * @return a list of patient having the same name
     */
    List<PatientDAO> findByName(String name);

    /**
     * Request to DB with patient's id param
     * @param idPatient
     * @return a patient
     */
    PatientDAO findByIdPatient(int idPatient);
}
