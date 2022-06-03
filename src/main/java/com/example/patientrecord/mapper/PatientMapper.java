package com.example.patientrecord.mapper;

import com.example.patientrecord.collection.Patient;
import com.example.patientrecord.model.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PatientMapper {

    @Autowired
    private ModelMapper modelMapper;


    public Function<Patient, PatientDTO> convertEntityToDTO(){
        return patient ->
                modelMapper.map(patient, PatientDTO.class);
    }

    public  Function<PatientDTO, Patient> convertDTOToEntity (){
        return patientDTO ->
                modelMapper.map(patientDTO,Patient.class);
    }
}
