package com.MediScreen.MediScreen.Controllers;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import com.MediScreen.MediScreen.Models.DTO.PatientDTO;
import com.MediScreen.MediScreen.Services.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PatientsControllerTest {

    @MockBean
    private IPatientService iPatientService;

    @Autowired
    private PatientsController patientsController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    /**
     * Method under test: {@link PatientsController#getAllPatients()}
     */
    @Test
    void testGetAllPatients() throws Exception {

        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");

        List<PatientDAO> list = new ArrayList<>();
        list.add(patientDAO);
        when(iPatientService.getAllPatients()).thenReturn(list);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/patient/allPatients").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Method under test: {@link PatientsController#getPatientByName(String)}
     */
    @Test
    void testGetPatientByName() throws Exception {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("?");
        patientDAO.setIdPatient(1);
        patientDAO.setName("?");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");

        ArrayList<PatientDAO> list = new ArrayList<>();
        list.add(patientDAO);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        when(iPatientService.getPatientByName((anyString()))).thenReturn(list);
        mvc.perform(get("/patient/patientName").param("name", patientDAO.getName()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link PatientsController#getPatientByFullName(String, String)}
     */
    @Test
    void testGetPatientByFullName() throws Exception {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        ArrayList<PatientDAO> list = new ArrayList<>();
        when(iPatientService.getPatientByFullName(anyString(), anyString())).thenReturn(patientDAO);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/patient/patientName&firstName").param("name", patientDAO.getName()).param("firstName",patientDAO.getFirstName()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }



    /**
     * Method under test: {@link PatientsController#getPatientById(int)}
     */
    @Test
    void testGetPatientById() throws Exception {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        Optional<PatientDAO> result = Optional.of(patientDAO);
        when(iPatientService.getPatientById(anyInt())).thenReturn(result);
        when(iPatientService.getPatientByFullName(anyString(), anyString())).thenReturn(patientDAO);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/patient/patientId").param("id", "1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link PatientsController#getPatientById(int)}
     */
    @Test
    void testGetPatientByIdIfNull() throws Exception {
        when(iPatientService.getPatientById(anyInt())).thenReturn(Optional.empty());
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/patient/patientId").param("id", "1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }



    /**
     * Method under test: {@link PatientsController#savePatient(PatientDTO)}
     */
    @Test
    void testSavePatient() throws Exception {

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddress("42 Main St");
        patientDTO.setDob("Dob");
        patientDTO.setFamily("Family");
        patientDTO.setGiven("Given");
        patientDTO.setPhone("4105551212");
        patientDTO.setSex("Sex");

        ObjectMapper mapper = new ObjectMapper();
        String patientToString = mapper.writeValueAsString(patientDTO);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientToString))
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link PatientsController#updatePatient(PatientDAO)}
     */
    @Test
    void testUpdatePatient() throws Exception {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        when(iPatientService.updatePatient((PatientDAO) any())).thenReturn(patientDAO);
        ObjectMapper mapper = new ObjectMapper();
        String patientToString = mapper.writeValueAsString(patientDAO);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(post("/patient/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientToString))
                .andExpect(status().isOk());

    }
}

