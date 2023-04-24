package com.mediscreen.DiabetesAssessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private int historyId;
    private int patientId;
    String patientName;
    String dateOfInterview;
    String commentary;

}
