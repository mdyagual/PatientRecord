package com.example.patientrecord.usecases.interfaces;

import com.example.patientrecord.model.PatientDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UpdatePatient {
    Mono<PatientDTO> apply (String id, PatientDTO pdto);
}
