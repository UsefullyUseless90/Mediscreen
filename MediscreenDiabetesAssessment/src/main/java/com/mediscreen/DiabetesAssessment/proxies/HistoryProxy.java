package com.mediscreen.DiabetesAssessment.proxies;

import com.mediscreen.DiabetesAssessment.model.History;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "HistoryApplication", url = "http://localhost:8082/patHistory")
public interface HistoryProxy {

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/historyId", params = "id")
    ResponseEntity<?> getHistoryById(@RequestParam int id);

    /**
     *
     * @param
     * @return
     */
    // In case of duplicates ??
    // (According to me this needs to have a firstname in addition,
    // to locate the history of the wanted patient)
    @RequestMapping(value = "/historiesByName", params = {"name","firstName"})
    List<History> getHistoriesByPatientName(@RequestParam String name, @RequestParam String firstName);

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/historiesId", params = "id" )
    List<History> getHistoriesById(@RequestParam int id);
}
