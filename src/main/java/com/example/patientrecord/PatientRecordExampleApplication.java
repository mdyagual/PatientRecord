package com.example.patientrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class PatientRecordExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientRecordExampleApplication.class, args);
	}

}
