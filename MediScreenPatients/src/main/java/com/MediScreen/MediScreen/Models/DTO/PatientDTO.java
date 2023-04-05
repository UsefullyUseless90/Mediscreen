package com.MediScreen.MediScreen.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class PatientDTO implements Serializable {

    private String given;
    private String family;
    private String dob;
    private String sex;
    private String address;
    private String phone;

    public PatientDTO() {
    }
}
