package com.MediScreen.MediScreen.Models.DAO;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Locale;


@Data
@Table(name = "patients_informations")
@Entity
@Getter
@Setter
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

    public PatientDAO(int idPatient,String name, String firstName, String birthDate, String gender, String postalAddress, String phoneNumber) {
        this.idPatient = idPatient;
        this.name = name;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.postalAddress = postalAddress;
        this.phoneNumber = phoneNumber;
    }

    public PatientDAO() {
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
