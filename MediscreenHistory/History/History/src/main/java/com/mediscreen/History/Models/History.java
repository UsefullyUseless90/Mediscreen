package com.mediscreen.History.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class History {

    int historyId;
    int patientId;
    String dateOfInterview;
    String commentary;

    public History(int historyId,int patientId, String dateOfInterview, String commentary) {
        this.historyId = historyId;
        this.patientId = patientId;
        this.dateOfInterview = dateOfInterview;
        this.commentary = commentary;
    }
}
