package com.mediscreen.History.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class History {

    int patientId;
    String patientName;
    String patientFirstName;
    String commentary;

    public History(int patientId, String patientName, String patientFirstName, String commentary) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientFirstName = patientFirstName;
        this.commentary = commentary;
    }
}
