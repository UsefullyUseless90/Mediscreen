package com.MediScreen.MediScreen.Controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.MediScreen.MediScreen.Models.DAO.PatientDAO;
import com.MediScreen.MediScreen.Models.DTO.PatientDTO;
import com.MediScreen.MediScreen.Repositories.PatientRepository;
import com.MediScreen.MediScreen.Services.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PatientsController.class})
@ExtendWith(SpringExtension.class)
class PatientsControllerTest {
    @MockBean
    private IPatientService iPatientService;

    @Autowired
    private PatientsController patientsController;

    /**
     * Method under test: {@link PatientsController#getAllPatients()}
     */
    @Test
    void testGetAllPatients() throws Exception {
        when(this.iPatientService.getAllPatients()).thenReturn((Iterable<PatientDAO>) mock(Iterable.class));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/patient/allPatients");
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.patientsController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
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

        ArrayList<PatientDAO> patientDAOList = new ArrayList<>();
        patientDAOList.add(patientDAO);
        when(this.iPatientService.getPatientByName((String) any())).thenReturn(patientDAOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patient/patientName")
                .param("name", "foo");
        MockMvcBuilders.standaloneSetup(this.patientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"idPatient\":1,\"name\":\"?\",\"firstName\":\"Jane\",\"birthDate\":\"2020-03-01\",\"gender\":\"?\",\"postalAddress\":\"42"
                                        + " Main St\",\"phoneNumber\":\"4105551212\"}]"));
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
        when(this.iPatientService.getPatientByFullName((String) any(), (String) any())).thenReturn(patientDAO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patient/patientName&firstName")
                .param("firstName", "foo")
                .param("name", "foo");
        MockMvcBuilders.standaloneSetup(this.patientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idPatient\":1,\"name\":\"Name\",\"firstName\":\"Jane\",\"birthDate\":\"2020-03-01\",\"gender\":\"Gender\",\"postalAddress"
                                        + "\":\"42 Main St\",\"phoneNumber\":\"4105551212\"}"));
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
        Optional<PatientDAO> ofResult = Optional.of(patientDAO);
        when(this.iPatientService.getPatientById(anyInt())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/patient/patientId");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.patientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idPatient\":1,\"name\":\"Name\",\"firstName\":\"Jane\",\"birthDate\":\"2020-03-01\",\"gender\":\"Gender\",\"postalAddress"
                                        + "\":\"42 Main St\",\"phoneNumber\":\"4105551212\"}"));
    }

    /**
     * Method under test: {@link PatientsController#getPatientById(int)}
     */
    @Test
    void testGetPatientByIdIfNull() throws Exception {
        when(this.iPatientService.getPatientById(anyInt())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/patient/patientId");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.patientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }

    /**
     * Method under test: {@link PatientsController#savePatient(PatientDTO)}
     */
    @Test
    void testSavePatient() throws Exception {
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.setBirthDate("2020-03-01");
        patientDAO.setFirstName("Jane");
        patientDAO.setGender("Gender");
        patientDAO.setIdPatient(1);
        patientDAO.setName("Name");
        patientDAO.setPhoneNumber("4105551212");
        patientDAO.setPostalAddress("42 Main St");
        when(this.iPatientService.savePatient((PatientDAO) any())).thenReturn(patientDAO);

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddress("42 Main St");
        patientDTO.setDob("Dob");
        patientDTO.setFamily("Family");
        patientDTO.setGiven("Given");
        patientDTO.setPhone("4105551212");
        patientDTO.setSex("Sex");
        String content = (new ObjectMapper()).writeValueAsString(patientDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.patientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idPatient\":0,\"name\":\"Family\",\"firstName\":\"Given\",\"birthDate\":\"Dob\",\"gender\":\"Sex\",\"postalAddress\":\"42"
                                        + " Main St\",\"phoneNumber\":\"4105551212\"}"));
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
        when(this.iPatientService.updatePatient((PatientDAO) any())).thenReturn(patientDAO);

        PatientDAO patientDAO1 = new PatientDAO();
        patientDAO1.setBirthDate("2020-03-01");
        patientDAO1.setFirstName("Jane");
        patientDAO1.setGender("Gender");
        patientDAO1.setIdPatient(1);
        patientDAO1.setName("Name");
        patientDAO1.setPhoneNumber("4105551212");
        patientDAO1.setPostalAddress("42 Main St");
        String content = (new ObjectMapper()).writeValueAsString(patientDAO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patient/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.patientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idPatient\":1,\"name\":\"Name\",\"firstName\":\"Jane\",\"birthDate\":\"2020-03-01\",\"gender\":\"Gender\",\"postalAddress"
                                        + "\":\"42 Main St\",\"phoneNumber\":\"4105551212\"}"));
    }
}

