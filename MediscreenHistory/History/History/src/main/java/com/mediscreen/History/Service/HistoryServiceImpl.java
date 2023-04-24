package com.mediscreen.History.Service;

import com.mediscreen.History.Models.History;
import com.mediscreen.History.Repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    HistoryRepository historyRepository;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * method in charge to get all histories
     * @return a list of histories
     */
    public Iterable<History>getAllHistories(){
        Iterable<History> histories = historyRepository.findAll();
        return histories;
    }

    /**
     * method in charge of getting all histories of a patient by his id
     * @param patientId
     * @return a list of histories
     */

    public List<History> getHistoriesByPatientId(int patientId){
        List<History> histories = historyRepository.findByPatientId(patientId);
        return histories;
    }

    /**
     * method in charge of getting all histories of a patient by his full name
     * @param name
     * @param firstName
     * @return a list of histories
     */
    public List<History> getHistoriesByPatientNameAndFirstName(String name, String firstName){
        List<History> histories = historyRepository.findByPatientNameAndPatientFirstName(name,firstName);
        return histories;
    }

    /**
     * method in charge of getting history by its id
     * @param historyId
     * @return a history
     */
    public History getHistoryById(int historyId){
        History history = historyRepository.findByHistoryId(historyId);
        return history;
    }

    /**
     * method in charge of adding a new history
     * @param history
     * @return history
     */
    @Transactional
    public History addNewHistory(History history){
        history.setHistoryId(new Random().nextInt());
        Date date = new Date();
        history.setDateOfInterview(sdf.format(date));
        historyRepository.save(history);
        return history;
    }

    /**
     * method in charge of updating an existing history
     * @param history
     * @return an updated history
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
