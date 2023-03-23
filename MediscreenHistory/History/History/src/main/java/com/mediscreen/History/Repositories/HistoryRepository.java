package com.mediscreen.History.Repositories;

import com.mediscreen.History.Models.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {

    List<History>findByPatientId(int idPatient);
}
