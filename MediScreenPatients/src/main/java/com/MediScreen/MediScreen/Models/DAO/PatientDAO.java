package com.MediScreen.MediScreen.Models.DAO;

import com.MediScreen.MediScreen.Configuration.JacocoExclusion;
import com.MediScreen.MediScreen.Models.DTO.PatientDTO;
import com.sun.istack.NotNull;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Table(name = "patients_informations")
@Entity
@Getter
@Setter
@NoArgsConstructor

public class PatientDAO {
    @Id
    @Column(name = "id_patient")
    private int idPatient;
    @NotNull
    @NotBlank(message = "This can't be empty, informations needed!")
    @Column(name = "name")
    private String name;
    @NotNull
    @NotBlank(message = "This can't be empty, informations needed!")
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @NotBlank(message = "This can't be empty, informations needed!")
    @Column(name = "birth_date")
    private String birthDate;
    @NotNull
    @NotBlank(message = "This can't be empty, informations needed!")
    @Column(name = "gender")
    private String gender;
    @NotNull
    @NotBlank(message = "This can't be empty, informations needed!")
    @Column(name = "postal_address")
    private String postalAddress;
    @NotNull
    @NotBlank(message = "This can't be empty, informations needed!")
    @Column(name = "phone_number")
    private String phoneNumber;

    public PatientDAO(PatientDTO patientDTO){
        this.setName(patientDTO.getFamily());
        this.setFirstName(patientDTO.getGiven());
        this.setBirthDate(patientDTO.getDob());
        this.setGender(patientDTO.getSex());
        this.setPostalAddress(patientDTO.getAddress());
        this.setPhoneNumber(patientDTO.getPhone());
    }




}
