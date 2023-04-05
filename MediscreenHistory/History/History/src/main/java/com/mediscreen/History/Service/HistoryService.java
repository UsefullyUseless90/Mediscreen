package com.mediscreen.History.Service;

import com.mediscreen.History.Models.History;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    Iterable<History>getAllHistories();
    List<History> getHistoriesByPatientName(String patientName);
    History getHistoryById(int historyId);
    History addNewHistory(History history);
    History updateExistingHistory(History history);
}
