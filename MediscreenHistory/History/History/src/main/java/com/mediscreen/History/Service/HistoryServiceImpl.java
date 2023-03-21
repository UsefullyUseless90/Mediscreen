package com.mediscreen.History.Service;

import com.mediscreen.History.Models.History;
import com.mediscreen.History.Repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    HistoryRepository historyRepository;

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
     * @param idPatient
     * @return
     */

    public History getHistoryById(int idPatient){
        return historyRepository.findByPatientId(idPatient);
    }

    /**
     *
     * @param history
     * @return
     */

    public History addNewHistory(History history){
        historyRepository.save(history);
        return history;
    }

    /**
     *
     * @param history
     * @return
     */

    public History updateExistingHistory(History history){
        History updatedHistory = new History();
        historyRepository.findById(history.getPatientId());
        updatedHistory.setPatientFirstName(history.getPatientFirstName());
        updatedHistory.setPatientName(history.getPatientName());
        updatedHistory.setCommentary(history.getCommentary());
        historyRepository.save(updatedHistory);
        return updatedHistory;
    }
}
