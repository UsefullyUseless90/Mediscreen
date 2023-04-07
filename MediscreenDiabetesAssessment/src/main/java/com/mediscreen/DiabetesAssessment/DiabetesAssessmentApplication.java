package com.mediscreen.DiabetesAssessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.mediscreen.DiabetesAssessment.proxies")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DiabetesAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiabetesAssessmentApplication.class, args);
	}

}
