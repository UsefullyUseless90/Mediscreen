package com.mediscreen.History.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.History.Models.DTO.HistoryDTO;
import com.mediscreen.History.Models.History;
import com.mediscreen.History.Repositories.HistoryRepository;
import com.mediscreen.History.Service.HistoryServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HistoryControllerTest {

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistoryRepository historyRepository;

    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    History history1;
    History history2;
    History history3;
    HistoryDTO historyDTO;
    HistoryDTO historyDTO1;
    HistoryDTO historyDTO2;

    private List<History> histories = new ArrayList();
    private List<HistoryDTO> historyDTOList = new ArrayList<>();

    @Before
    public void setup() {

        //History history1 = new History();
        history1.setHistoryId(10);
        history1.setPatientId(01);
        history1.setPatientName("Name");
        history1.setPatientFirstName("firstname");
        history1.setDateOfInterview("2023-03-23");
        history1.setCommentary("blablablabla");

        //History history2 = new History();
        history2.setHistoryId(20);
        history2.setPatientId(02);
        history2.setPatientName("fake");
        history2.setPatientFirstName("fakefirstname");
        history2.setDateOfInterview("2023-04-23");
        history2.setCommentary("blablablablaM");

        //History history3 = new History();
        history3.setHistoryId(30);
        history3.setPatientId(03);
        history3.setPatientName("fakeAgain");
        history3.setPatientFirstName("fakeAgainfirstname");
        history3.setDateOfInterview("2023-05-23");
        history3.setCommentary("dugfirgoqdrhgmoqer");

        histories.add(history1);
        histories.add(history2);
        histories.add(history3);

        historyDTO.setPatientName("");
        historyDTO.setPatientId(222222);
        historyDTO.setPractitionerSNotesRecommandation("");

        historyDTO1.setPatientName("fgsqdgdsf");
        historyDTO1.setPatientId(222222);
        historyDTO1.setPractitionerSNotesRecommandation("qfgqfgsdqfgdqfg");

        historyDTO2.setPatientName("eeeeeeeee");
        historyDTO2.setPatientId(222222);
        historyDTO2.setPractitionerSNotesRecommandation("gggggggggggggg");

        historyDTOList.add(historyDTO1);
        historyDTOList.add(historyDTO);
        historyDTOList.add(historyDTO2);


    }

    /**
     * Method under test: {@link HistoryController#getAllHistories()}
     */
    @Test
    void testGetAllHistories() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        // when(historyRepository.findAll()).thenReturn(histories);
        mvc.perform(get("/patHistory/allHistories").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link HistoryController#getHistoriesByPatientId(int)}
     */
    @Test
    void testGetHistoriesByPatientId() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/patHistory/historiesId?id=1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link HistoryController#getHistoryById(int)}
     */
    @Test
    void testGetHistoryById() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/patHistory/historyId?id=1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link HistoryController#getHistoryByPatientName(String, String)}
     */
    @Test
    void testGetHistoryByPatientName() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(get("/patHistory/historiesByName?name=langley&firstName=falls").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link HistoryController#saveHistory(HistoryDTO)}
     */
    @Test
    void testSaveHistory() throws Exception {
        HistoryDTO historyDTO3 = new HistoryDTO();
        historyDTO3.setPatientName("");
        historyDTO3.setPatientId(222222);
        historyDTO3.setPractitionerSNotesRecommandation("");
        ObjectMapper mapper = new ObjectMapper();
        String historyToString = mapper.writeValueAsString(historyDTO3);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(post("/patHistory/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(historyToString))
                .andExpect(status().isOk());
    }

    /**
     * Method under test: {@link HistoryController#updateHistory(History)}
     */
    @Test
    void testUpdateHistoryWithClientError() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(put("/patHistory/edit", history1))
                        .andExpect(status().is4xxClientError());

    }

    /**
     * Method under test: {@link HistoryController#updateHistory(History)}
     */
    /*
    @Test
    void testUpdateHistory() throws Exception {
        History history = new History();
        history.setHistoryId(10);
        history.setPatientId(01);
        history.setPatientName("Name");
        history.setPatientFirstName("firstname");
        history.setDateOfInterview("2023-03-23");
        history.setCommentary("blablablabla");

        History history1 = new History();
        history1.setHistoryId(10);
        history1.setPatientId(01);
        history1.setPatientName("Name");
        history1.setPatientFirstName("firstname");
        history1.setDateOfInterview("2023-03-23");
        history1.setCommentary("blablablabla");
        when(historyService.updateExistingHistory(history)).thenReturn(history1);
        ObjectMapper mapper = new ObjectMapper();
        String historyToString = mapper.writeValueAsString(history1);
        //when(historyRepository.findByHistoryId(anyInt())).thenReturn(history);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc.perform(put("/patHistory/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(historyToString))
                .andExpect(status().isOk());
    }

     */
}
