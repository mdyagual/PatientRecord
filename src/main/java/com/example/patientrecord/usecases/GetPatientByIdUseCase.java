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
public class GetPatientByIdUseCase implements Function<String, Mono<PatientDTO>> {
    private PatientMapper patientMapper;
    private IPatientRepository patientRepository;

    @Override
    public Mono<PatientDTO> apply (String id){
        return patientRepository.findById(id)
                .map(patient -> patientMapper.convertEntityToDTO().apply(patient));
    }


}
