package com.mediscreen.History.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.mediscreen.History.Controllers.HistoryController;
import com.mediscreen.History.Models.DTO.HistoryDTO;
import com.mediscreen.History.Models.History;
import com.mediscreen.History.Repositories.HistoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HistoryServiceImplTest {
    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistoryRepository historyRepository;

    HistoryDTO historyDTO;
    HistoryDTO historyDTO1;
    HistoryDTO historyDTO2;

    private List<History> histories = new ArrayList();
    private List<HistoryDTO> historyDTOList = new ArrayList<>();


    /**
     * Method under test: {@link HistoryServiceImpl#getAllHistories()}
     */
    @Test
    void testGetAllHistories(){

        History history1 = new History();
        history1.setHistoryId("10");
        history1.setPatientId(01);
        history1.setPatientName("Name");
        history1.setPatientFirstName("firstname");
        history1.setDateOfInterview("2023-03-23");
        history1.setCommentary("blablablabla");

        History history2 = new History();
        history2.setHistoryId("20");
        history2.setPatientId(02);
        history2.setPatientName("fake");
        history2.setPatientFirstName("fakefirstname");
        history2.setDateOfInterview("2023-04-23");
        history2.setCommentary("blablablablaM");

        History history3 = new History();
        history3.setHistoryId("30");
        history3.setPatientId(03);
        history3.setPatientName("fakeAgain");
        history3.setPatientFirstName("fakeAgainfirstname");
        history3.setDateOfInterview("2023-05-23");
        history3.setCommentary("dugfirgoqdrhgmoqer");

        histories.add(history1);
        histories.add(history2);
        histories.add(history3);

        when(historyRepository.findAll()).thenReturn(histories);
        List<History> verifyList = historyRepository.findAll();
        assertThat(verifyList.size()).isEqualTo(3);
        assertThat(verifyList.contains(history1));
        assertThat(verifyList.contains(history2));
        assertThat(verifyList.contains(history3));
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getHistoriesByPatientId(int)}
     */
    @Test
    void testGetHistoriesByPatientId(){

        History history = new History();
        history.setHistoryId("10");
        history.setPatientId(1);
        history.setPatientName("Name");
        history.setPatientFirstName("firstname");
        history.setDateOfInterview("2023-03-23");
        history.setCommentary("blablablabla");

        History history1 = new History();
        history1.setHistoryId("10");
        history1.setPatientId(1);
        history1.setPatientName("Name");
        history1.setPatientFirstName("firstname");
        history1.setDateOfInterview("2023-03-23");
        history1.setCommentary("blablablabla");

        histories.add(history);
        histories.add(history1);

        when(historyRepository.findByPatientId(Mockito.anyInt())).thenReturn(histories);
        Assert.assertEquals(histories, historyService.getHistoriesByPatientId(1));
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getHistoryById(int)}
     */
    @Test
    void testGetHistoryById(){

        History history = new History();
        history.setHistoryId("10");
        history.setPatientId(1);
        history.setPatientName("Name");
        history.setPatientFirstName("firstname");
        history.setDateOfInterview("2023-03-23");
        history.setCommentary("blablablabla");

        when(historyRepository.findByHistoryId("10")).thenReturn(history);
        Assert.assertEquals(history, historyService.getHistoryById("10"));
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getHistoriesByPatientNameAndFirstName(String, String)}
     */
    @Test
    void testGetHistoryByPatientNameAndFirstName(){

        History history = new History();
        history.setHistoryId("10");
        history.setPatientId(1);
        history.setPatientName("Name");
        history.setPatientFirstName("firstname");
        history.setDateOfInterview("2023-03-23");
        history.setCommentary("blablablabla");

        History history1 = new History();
        history1.setHistoryId("11");
        history1.setPatientId(01);
        history1.setPatientName("Name");
        history1.setPatientFirstName("firstname");
        history1.setDateOfInterview("2023-03-23");
        history1.setCommentary("blablablabla");

        histories.add(history);
        histories.add(history1);

        when(historyRepository.findByPatientNameAndPatientFirstName("Name","firstname")).thenReturn(histories);
        Assert.assertEquals(histories, historyService.getHistoriesByPatientNameAndFirstName(history.getPatientName(), history.getPatientFirstName()));
    }

    /**
     * Method under test: {@link HistoryServiceImpl#addNewHistory(History)}
     */
    @Test
    void testAddNewHistory() {

        History history = new History();
        history.setHistoryId("10");
        history.setPatientId(1);
        history.setPatientName("Name");
        history.setPatientFirstName("firstname");
        history.setDateOfInterview("2023-03-23");
        history.setCommentary("blablablabla");

        when(historyRepository.save(history)).thenReturn(history);
        historyService.addNewHistory(history);
        verify(historyRepository,times(1)).save(history);
    }

    /**
     * Method under test: {@link HistoryServiceImpl#updateExistingHistory(History)}
     */
    @Test
    void testUpdateExistingHistory() {
        History history = new History();
        history.setHistoryId("10");
        history.setPatientId(1);
        history.setPatientName("Name");
        history.setPatientFirstName("firstname");
        history.setDateOfInterview("2023-03-23");
        history.setCommentary("blablablabla");

        History historyUpdated = new History();
        historyUpdated.setHistoryId("10");
        historyUpdated.setPatientId(1);
        historyUpdated.setPatientName("Name");
        historyUpdated.setPatientFirstName("firstname");
        historyUpdated.setDateOfInterview("33323232323");
        historyUpdated.setCommentary("blablablabla");

        //when(historyRepository.findByHistoryId(10)).thenReturn(history);
        //when(historyService.updateExistingHistory(history)).thenReturn(historyUpdated);
        Assert.assertEquals(historyUpdated.getHistoryId(), "10");
        Assert.assertEquals(historyUpdated.getPatientId(), 1);
        Assert.assertEquals(historyUpdated.getPatientName(), "Name");
        Assert.assertEquals(historyUpdated.getPatientFirstName(), "firstname");
        Assert.assertEquals(historyUpdated.getDateOfInterview(),"33323232323");
        Assert.assertEquals(historyUpdated.getCommentary(), "blablablabla");

    }



}

