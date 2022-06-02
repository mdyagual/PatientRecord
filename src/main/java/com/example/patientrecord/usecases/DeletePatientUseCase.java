package com.example.patientrecord.usecases;

import com.example.patientrecord.mapper.PatientMapper;
import com.example.patientrecord.repository.IPatientRepository;
import com.example.patientrecord.usecases.interfaces.DeletePatient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeletePatientUseCase implements DeletePatient {

    private final IPatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Mono<Void> apply(String id){
        return patientRepository.deleteById(id);
    }


}
