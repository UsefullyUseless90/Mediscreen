package com.mediscreen.DiabetesAssessment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HistoryTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link History#History()}
     *   <li>{@link History#setCommentary(String)}
     *   <li>{@link History#setDateOfInterview(String)}
     *   <li>{@link History#setHistoryId(int)}
     *   <li>{@link History#setPatientId(int)}
     *   <li>{@link History#setPatientName(String)}
     * </ul>
     */
    @Test
    void testConstructor() {
        History actualHistory = new History();
        actualHistory.setCommentary("Commentary");
        actualHistory.setDateOfInterview("2020-03-01");
        actualHistory.setHistoryId(123);
        actualHistory.setPatientId(123);
        actualHistory.setPatientName("Patient Name");
        assertEquals("Commentary", actualHistory.getCommentary());
        assertEquals("2020-03-01", actualHistory.getDateOfInterview());
        assertEquals(123, actualHistory.getHistoryId());
        assertEquals(123, actualHistory.getPatientId());
        assertEquals("Patient Name", actualHistory.getPatientName());
    }


}

