package com.example.patientrecord.usecases;

import com.example.patientrecord.mapper.PatientMapper;
import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.repository.IPatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllPatientsUseCase implements Supplier<Flux<PatientDTO>> {

    private PatientMapper patientMapper;
    private IPatientRepository patientRepository;


    @Override
    public Flux<PatientDTO> get(){
        return patientRepository.findAll().map(patient -> patientMapper.convertEntityToDTO().apply(patient));
    }
}
