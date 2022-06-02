package com.example.patientrecord.usecases;

import com.example.patientrecord.collection.Patient;
import com.example.patientrecord.mapper.PatientMapper;
import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.repository.IPatientRepository;
import com.example.patientrecord.usecases.interfaces.SavePatient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SavePatientUseCase implements SavePatient {
    private final IPatientRepository patientRepo;
    private final PatientMapper patientMapper;

    public SavePatientUseCase(IPatientRepository patientRepo, PatientMapper patientMapper) {
        this.patientRepo = patientRepo;
        this.patientMapper = patientMapper;
    }

    @Override
    public Mono<PatientDTO> apply(PatientDTO pdto){
        return patientRepo.save(patientMapper.convertDTOToEntity().apply(pdto)).map(patient -> patientMapper.convertEntityToDTO().apply(patient));
    }
}
