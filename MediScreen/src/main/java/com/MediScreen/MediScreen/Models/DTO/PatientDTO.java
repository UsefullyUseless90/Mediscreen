package com.MediScreen.MediScreen.Models.DTO;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class PatientDTO {

    @Column(name = "name")
    private String name;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "birthDate")
    private Date birthDate;

}
