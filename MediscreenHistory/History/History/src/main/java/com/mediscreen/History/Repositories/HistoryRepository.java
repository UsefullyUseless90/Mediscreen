package com.mediscreen.History.Repositories;

import com.mediscreen.History.Models.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {

    History findByPatientId(int idPatient);
}
