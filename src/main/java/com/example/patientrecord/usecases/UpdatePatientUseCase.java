package com.example.patientrecord.usecases;

import com.example.patientrecord.mapper.PatientMapper;
import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.repository.IPatientRepository;
import com.example.patientrecord.usecases.interfaces.UpdatePatient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdatePatientUseCase implements UpdatePatient {
    private final IPatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Mono<PatientDTO> apply(String id, PatientDTO pdto){
        return patientRepository.findById(id).flatMap(patient -> {
            pdto.setId(patient.getId());
            return patientRepository.save(patientMapper.convertDTOToEntity().apply(pdto)).map(patientRes -> patientMapper.convertEntityToDTO().apply(patientRes));
        }).switchIfEmpty(Mono.just(new PatientDTO()));
    }

}
