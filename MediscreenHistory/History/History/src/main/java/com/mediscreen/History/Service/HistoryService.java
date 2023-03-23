package com.mediscreen.History.Service;

import com.mediscreen.History.Models.History;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    Iterable<History>getAllHistories();
    List<History> getHistoriesById(int idPatient);
    History addNewHistory(History history);
    History updateExistingHistory(History history);
}
