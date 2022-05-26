package com.example.patientrecord.repository;

import com.example.patientrecord.collection.Patient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends ReactiveMongoRepository<Patient,String> {
}
