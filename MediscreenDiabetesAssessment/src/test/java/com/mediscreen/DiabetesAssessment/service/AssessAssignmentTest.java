package com.mediscreen.DiabetesAssessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.mediscreen.DiabetesAssessment.proxies.HistoryProxy;
import com.mediscreen.DiabetesAssessment.proxies.PatientProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AssessAssignment.class})
@ExtendWith(SpringExtension.class)
class AssessAssignmentTest {
    @Autowired
    private AssessAssignment assessAssignment;

    @MockBean
    private HistoryProxy historyProxy;

    @MockBean
    private PatientProxy patientProxy;


    /**
     * Method under test: {@link AssessAssignment#assignAssessMale(int)}
     */
    @Test
    void testAssignAssessMale() {
        assertEquals("INDANGER", this.assessAssignment.assignAssessMale(3));
        assertEquals("BORDERLINE", this.assessAssignment.assignAssessMale(2));
        assertEquals("EARLYONSET", this.assessAssignment.assignAssessMale(6));
        assertEquals("NONE", this.assessAssignment.assignAssessMale(1));
        assertNull(this.assessAssignment.assignAssessMale(-1));
    }

    /**
     * Method under test: {@link AssessAssignment#assignAssessFemale(int)}
     */
    @Test
    void testAssignAssessFemale() {
        assertEquals("BORDERLINE", this.assessAssignment.assignAssessFemale(3));
        assertEquals("INDANGER", this.assessAssignment.assignAssessFemale(4));
        assertEquals("NONE", this.assessAssignment.assignAssessFemale(1));
        assertEquals("EARLYONSET", this.assessAssignment.assignAssessFemale(7));
        assertNull(this.assessAssignment.assignAssessFemale(-1));
    }

    /**
     * Method under test: {@link AssessAssignment#assignAssessThirtyPlus(int)}
     */
    @Test
    void testAssignAssessThirtyPlus() {
        assertEquals("BORDERLINE", this.assessAssignment.assignAssessThirtyPlus(3));
        assertEquals("INDANGER", this.assessAssignment.assignAssessThirtyPlus(6));
        assertEquals("EARLYONSET", this.assessAssignment.assignAssessThirtyPlus(8));
        assertEquals("NONE", this.assessAssignment.assignAssessThirtyPlus(1));
        assertNull(this.assessAssignment.assignAssessThirtyPlus(-1));
    }
}

