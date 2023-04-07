package com.mediscreen.History.Controllers;

import com.mediscreen.History.Models.DTO.HistoryDTO;
import com.mediscreen.History.Models.History;
import com.mediscreen.History.Repositories.HistoryRepository;
import com.mediscreen.History.Service.HistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RestController
@RequestMapping("/patHistory")
public class HistoryController {

    @Autowired
    HistoryService historyService;
    @Autowired
    HistoryRepository historyRepository;

    Logger log = LogManager.getLogger(HistoryController.class);

    /**
     *
     * @return
     */
    @RequestMapping("/allHistories")
    public ResponseEntity<Iterable<History>>getAllHistories(){
        Iterable<History> histories = historyService.getAllHistories();
        log.info("this is all the histories: " + histories);
        return ResponseEntity.ok(histories);
    }

    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/historiesId", params = "id")
    public ResponseEntity<?> getHistoriesByPatientId(@RequestParam int id){
        List<History> histories= historyService.getHistoriesByPatientId(id);
        log.info("The following history is for the id received: " + histories);
        return ResponseEntity.status(HttpStatus.OK).body(histories);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/historyId", params = "id")
    public ResponseEntity<?> getHistoryById(@RequestParam int id){
        History history = historyService.getHistoryById(id);
        log.info("The following history is for the id received: " + history);
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    @RequestMapping(value = "/historiesByName", params = {"name","firstName"})
    public ResponseEntity<?> getHistoryByPatientName(@RequestParam String name, String firstName){
        List<History> histories = historyService.getHistoriesByPatientNameAndFirstName(name, firstName);
        log.info("The following histories is for the id received: " + histories);
        return ResponseEntity.status(HttpStatus.OK).body(histories);
    }


    /**
     *
     * @param historyDTO
     * @return
     */

    @PostMapping(path = "/add")
    public ResponseEntity<?> saveHistory(@RequestBody HistoryDTO historyDTO){
        History history = new History(historyDTO);
        historyService.addNewHistory(history);
        log.info("New history added: " + history);
        return ResponseEntity.ok(history);
    }

    /**
     *
     * @param history
     * @return
     */

    @PutMapping
    @RequestMapping(value="/edit")
    public History updateHistory(@RequestBody History history){
        History update = historyService.updateExistingHistory(history);
        log.info("Patient infos updated!" + update);
        return history;
    }

}
