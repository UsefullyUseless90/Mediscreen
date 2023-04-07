package com.mediscreen.DiabetesAssessment.proxies;

import com.mediscreen.DiabetesAssessment.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "mediscreenApplication", url = "http://localhost:8081")
public interface PatientProxy {

    @RequestMapping(value = "/patient/patientId", params = "id")
    Patient getPatientById(@RequestParam int id);

    @RequestMapping(value = "/patient/patientName&firstName", params = {"name","firstName"})
    Patient getPatientByName(@RequestParam String name , @RequestParam String firstName);

}
