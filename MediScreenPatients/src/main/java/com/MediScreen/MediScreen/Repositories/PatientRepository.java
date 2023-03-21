package com.MediScreen.MediScreen.Repositories;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientDAO, Integer> {

    List<PatientDAO> findByNameOrFirstName(String name, String firstName);
    PatientDAO findByIdPatient(int idPatient);
}
