package com.mediscreen.DiabetesAssessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private int idPatient;
    private String name;
    private String firstName;
    private String birthDate;
    private String gender;
    private String postalAddress;
    private String phoneNumber;

}
