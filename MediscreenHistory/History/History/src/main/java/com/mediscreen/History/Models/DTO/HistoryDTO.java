package com.mediscreen.History.Models.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class HistoryDTO implements Serializable {

    private int patientId;
    private String patientName;
    private String practitionerSNotesRecommandation;

}
