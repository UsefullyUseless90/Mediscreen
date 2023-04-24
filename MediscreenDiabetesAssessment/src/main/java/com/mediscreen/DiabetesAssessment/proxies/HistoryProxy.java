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
     * endpoint with id params
     * @param id
     * @return
     */
    @RequestMapping(value = "/historyId", params = "id")
    ResponseEntity<?> getHistoryById(@RequestParam int id);

    /**
     *endpoint with full name params
     * @param
     * @return
     */
    @RequestMapping(value = "/historiesByName", params = {"name","firstName"})
    List<History> getHistoriesByPatientName(@RequestParam String name, @RequestParam String firstName);

    /**
     * endpoint with id params
     * @param id
     * @return
     */
    @RequestMapping(value = "/historiesId", params = "id" )
    List<History> getHistoriesById(@RequestParam int id);
}
