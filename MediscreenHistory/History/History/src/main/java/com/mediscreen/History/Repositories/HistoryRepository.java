package com.mediscreen.History.Repositories;

import com.mediscreen.History.Models.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {

    List<History>findByPatientName(String patientName);
    History findByHistoryId(@Param("idHistory") int idHistory);
    History deleteHistoryByHistoryId(int idHistory);
}
