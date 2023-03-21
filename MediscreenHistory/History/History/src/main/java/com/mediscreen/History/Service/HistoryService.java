package com.mediscreen.History.Service;

import com.mediscreen.History.Models.History;

import java.util.Optional;

public interface HistoryService {

    Iterable<History>getAllHistories();
    History getHistoryById(int idPatient);
    History addNewHistory(History history);
    History updateExistingHistory(History history);
}
