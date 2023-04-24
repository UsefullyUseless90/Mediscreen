package com.mediscreen.History.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mediscreen.History.Models.DTO.HistoryDTO;
import org.junit.jupiter.api.Test;

class HistoryTest {
    /**
     * Method under test: {@link History#History(HistoryDTO)}
     */
    @Test
    void testConstructor() {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setPatientId(123);
        historyDTO.setPatientName("Patient Name");
        historyDTO.setPractitionerSNotesRecommandation("Practitioner SNotes Recommandation");
        History actualHistory = new History(historyDTO);
        assertEquals("Practitioner SNotes Recommandation", actualHistory.getCommentary());
        assertEquals("Patient Name", actualHistory.getPatientName());
        assertEquals(123, actualHistory.getPatientId());
    }

}

