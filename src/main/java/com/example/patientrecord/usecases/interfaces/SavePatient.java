package com.example.patientrecord.usecases.interfaces;

import com.example.patientrecord.collection.Patient;
import com.example.patientrecord.model.PatientDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SavePatient {
    Mono<PatientDTO> apply (PatientDTO pdto);
}
