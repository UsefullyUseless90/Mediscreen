package com.mediscreen.History.Service;

import com.mediscreen.History.Models.History;
import com.mediscreen.History.Repositories.HistoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    HistoryRepository historyRepository;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @return
     */
    public Iterable<History>getAllHistories(){
        Iterable<History> histories = historyRepository.findAll();
        return histories;
    }

    /**
     *
     * @param
     * @return
     */

    public List<History> getHistoriesByPatientId(int patientId){
        List<History> histories = historyRepository.findByPatientId(patientId);
        return histories;
    }

    public List<History> getHistoriesByPatientNameAndFirstName(String name, String firstName){
        List<History> histories = historyRepository.findByPatientNameAndPatientFirstName(name,firstName);
        return histories;
    }

    /**
     *
     * @param historyId
     * @return
     */
    public History getHistoryById(String historyId){
        History history = historyRepository.findByHistoryId(historyId);
        return history;
    }

    /**
     *
     * @param history
     * @return
     */

    public History addNewHistory(History history){
        int id = new Random().nextInt();
        String idToString = String.valueOf(id);
        history.setHistoryId(idToString);
        Date date = new Date();
        history.setDateOfInterview(sdf.format(date));
        historyRepository.save(history);
        return history;
    }

    /**
     *
     * @param history
     * @return
     */
    @Transactional
    public History updateExistingHistory(History history){
        Date date = new Date();
        History updated = historyRepository.findByHistoryId(history.getHistoryId());
        updated.setDateOfInterview(sdf.format(date));
        updated.setCommentary(history.getCommentary());
        historyRepository.deleteHistoryByHistoryId(history.getHistoryId());
        historyRepository.save(updated);
        return updated;
    }
}
