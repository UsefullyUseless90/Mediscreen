package com.mediscreen.History.Controllers;

import com.mediscreen.History.Models.History;
import com.mediscreen.History.Repositories.HistoryRepository;
import com.mediscreen.History.Service.HistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
     * @param id
     * @return
     */
    @RequestMapping(value = "/historiesId", params = "id")
    public ResponseEntity<?> getHistoriesById(@RequestParam int id){
        List<History> histories= historyService.getHistoriesById(id);
        log.info("The follow history is for the id received: " + histories);
        return ResponseEntity.status(HttpStatus.OK).body(histories);
    }

    /**
     *
     * @param history
     * @return
     */

    @PostMapping(path = "/add")
    public ResponseEntity<?> saveHistory(@RequestBody History history){
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
        return update;
    }

}
