package com.mediscreen.History.Repositories;

import com.mediscreen.History.Models.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {
    /**
     * Request to DB with patient's id param
     * @param id
     * @return a list of histories
     */
    List<History>findByPatientId(int id);

    /**
     * Request to DB with patient's full name param
     * @param name
     * @param firstName
     * @return a list of histories
     */
    List<History>findByPatientNameAndPatientFirstName(String name, String firstName);

    /**
     * Request to DB with history's id param
     * @param idHistory
     * @return a history
     */
    History findByHistoryId(@Param("idHistory") int idHistory);

    /**
     * Request to DB with history's id param
     * @param idHistory
     * @return void
     */
    History deleteHistoryByHistoryId(int idHistory);
}
