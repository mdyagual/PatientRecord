package com.example.patientrecord;

import com.example.patientrecord.model.PatientDTO;
//import com.example.patientrecord.service.PatientService;
import com.example.patientrecord.usecases.GetPatientByIdUseCase;
import com.example.patientrecord.usecases.SavePatientUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.NoSuchElementException;

@SpringBootTest
public class PatientServiceTest {

    @MockBean
    SavePatientUseCase savePatientUseCase;

    @MockBean
    GetPatientByIdUseCase getPatientByIdUseCase;

    @Test
    @DisplayName("savePatient")

    void savePatient (){
        PatientDTO patientDTO = new PatientDTO("1","ASDFG-123","Mary Rojas",24,"ACTIVE","ENABLED");

        StepVerifier
                .create(Mono.just(Mockito.when(savePatientUseCase.apply(patientDTO))
                        .thenReturn(Mono.just(patientDTO)))).expectComplete();

    }

    @Test
    @DisplayName("patientById")
    void patientById (){
        PatientDTO patientDTOExpected = new PatientDTO("1","ASDFG-123","Mary Rojas",24,"ACTIVE","ENABLED");
        StepVerifier
                .create(Mono.just(Mockito.when(getPatientByIdUseCase.apply("1"))
                        .thenReturn(Mono.just(patientDTOExpected)))).expectComplete().verify();
    }

    @Test
    @DisplayName("patiendByIdNotFound")
    void patientByIdNotFound (){
        StepVerifier
                .create(Mono.just(Mockito.when(getPatientByIdUseCase.apply("2"))
                        .thenThrow(NoSuchElementException.class)).onErrorStop()).thenCancel().verify();
    }
    /*
    //Preguntar aclaración sobre mock
    @Autowired
    private PatientService patientService;

    @Test
    @DisplayName("savePatient")
    void savePatient (){
        PatientDTO patientDTO = new PatientDTO("1","ASDFG-123","Mary Rojas",24,"ACTIVE","ENABLED");
        StepVerifier.create(patientService.savePatient(patientDTO)).expectComplete();

    }

    @Test
    @DisplayName("patientById")
    void patientById (){
        PatientDTO patientDTO = new PatientDTO("1","ASDFG-123","Mary Rojas",24,"ACTIVE","ENABLED");
        

    }
*/




}
