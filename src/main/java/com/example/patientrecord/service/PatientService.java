package com.example.patientrecord.service;

import com.example.patientrecord.collection.Patient;
import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.repository.IPatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PatientService {
    @Autowired
    private IPatientRepository iPatientRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Flux<PatientDTO> findAllPatients() {
        return this.iPatientRepo.findAll().map( p -> convertEntityToDTO(p));
    }

    public Mono<PatientDTO> findPatientById(String id){
        return this.iPatientRepo.findById(id).map(p -> convertEntityToDTO(p) );
    }

    public Mono<PatientDTO> savePatient (PatientDTO p){
        return this.iPatientRepo.save(convertDTOToEntity(p)).map(patientDto -> convertEntityToDTO(patientDto));
    }

    public Mono<PatientDTO> updatePatient(String id, PatientDTO p){
        return this.findPatientById(id)
                .flatMap(patient -> {
                    p.setId(id);
                    return this.savePatient(p);
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Void> deletePatient (String id){

        return this.findPatientById(id).flatMap(patientDTO -> this.iPatientRepo.deleteById(id)).switchIfEmpty(Mono.empty());
    }

    public Mono<Void> deleteAllPatients (){
        return this.iPatientRepo.deleteAll();
    }

    public Flux<PatientDTO> getPatientByDoctor (String name){
        return this.findAllPatients().filter(patientDTO -> patientDTO.getDoctor().equals(name));
    }

    public Mono<PatientDTO> blockAttendance(String id){
        return this.findPatientById(id).flatMap(patientDTO -> {
            if(patientDTO.getStatusAssurance().equals("INACTIVE") || patientDTO.getStatusAssurance().equals("DOES NOT HAVE"))
                patientDTO.setStatusAttendance("Blocked");
                return this.savePatient(patientDTO);
        }).switchIfEmpty(Mono.empty());
    }

    public Mono<PatientDTO> updateAssuranceStatus(String id, String status){
        return this.findPatientById(id).flatMap(patientDTO -> {
            if(!patientDTO.getStatusAssurance().equals(status))
                patientDTO.setStatusAssurance(status);
                return this.savePatient(patientDTO);
        }).switchIfEmpty(Mono.empty());
    }

    public  Mono<PatientDTO> updateDoctor(String id, String name){
        return this.findPatientById(id).flatMap(patientDTO -> {
            if(!patientDTO.getDoctor().equals(name))
                patientDTO.setDoctor(name);
            return this.savePatient(patientDTO);
        }).switchIfEmpty(Mono.empty());
    }

    //Converters
    public PatientDTO convertEntityToDTO(Patient p){
        return modelMapper.map(p, PatientDTO.class);
    }

    public Patient convertDTOToEntity (PatientDTO pdto){
        return modelMapper.map(pdto,Patient.class);
    }
}
