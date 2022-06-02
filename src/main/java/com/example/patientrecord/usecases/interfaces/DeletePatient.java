package com.example.patientrecord.usecases.interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeletePatient {
    Mono<Void> apply(String id);
}
