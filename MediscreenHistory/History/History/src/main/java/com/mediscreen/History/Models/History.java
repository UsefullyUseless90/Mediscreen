package com.mediscreen.History.Models;

import com.mediscreen.History.Models.DTO.HistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "history")
public class History {

    @Field(targetType = FieldType.OBJECT_ID)
    private int historyId;
    private int patientId;
    String patientName;
    String patientFirstName;
    String dateOfInterview;
    String commentary;

    public History(HistoryDTO historyDTO) {
        this.patientId = historyDTO.getPatientId();
        this.patientName = historyDTO.getPatientName();
        this.commentary = historyDTO.getPractitionerSNotesRecommandation();
    }

}
