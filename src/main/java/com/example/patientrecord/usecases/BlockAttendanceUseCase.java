package com.example.patientrecord.usecases;

import com.example.patientrecord.mapper.PatientMapper;
import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.repository.IPatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class BlockAttendanceUseCase implements Function<String, Mono<String>> {
    private final PatientMapper patientMapper;
    private final IPatientRepository patientRepository;
    private final UpdatePatientUseCase updatePatientUseCase;

    @Override
    public Mono<String> apply(String id){
        return patientRepository.findById(id)
                .flatMap(patient -> {
                 if(patient.getStatusAssurance().equals("DOES NOT HAVE") || patient.getStatusAssurance().equals("INACTIVE")){
                     patient.setStatusAttendance("BLOCKED");
                     return updatePatientUseCase
                             .apply(id,patientMapper.convertEntityToDTO().apply(patient))
                             .thenReturn("Patient with id "+patient.getIdPatient()+" has been blocked");
                 }
                 return Mono.just("Patient with id "+patient.getIdPatient()+"has not been blocked because assurance status is "+patient.getStatusAssurance());
                });
    }
}
