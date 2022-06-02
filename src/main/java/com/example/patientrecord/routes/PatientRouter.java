package com.example.patientrecord.routes;

import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.usecases.GetAllPatientsUseCase;
import com.example.patientrecord.usecases.GetPatientByIdUseCase;
import com.example.patientrecord.usecases.SavePatientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PatientRouter {
    //SAVE PATIENT
    @Bean
    public RouterFunction<ServerResponse> guardarPacienteRouter(SavePatientUseCase savePatientUseCase){
        //Two options:
        //1
        /*return route(POST("/save/patient").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PatientDTO.class)
                .flatMap(savePatientUseCase::apply)
                .flatMap(result -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(result))
        );*/

        //2
        Function<PatientDTO, Mono<ServerResponse>> executeGuardar = patientDTO -> savePatientUseCase.apply(patientDTO)
                .flatMap(result -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result));

        return route(POST("/save/patient").and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(PatientDTO.class).flatMap(executeGuardar));



    }
    //GET ALL PATIENTS
    @Bean
    public RouterFunction<ServerResponse> obtenerPacientesRouter(GetAllPatientsUseCase getAllPatientsUseCase){
        return route(GET("/all"), request -> ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(getAllPatientsUseCase.get(),PatientDTO.class)));
    }

    //GET PATIENT BY ID
    @Bean RouterFunction<ServerResponse> obtenerPacientePorIdRouter(GetPatientByIdUseCase getPatientByIdUseCase){
        return route(GET("/patient/{id}"),
                request -> ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(getPatientByIdUseCase.apply(request.pathVariable("id")),PatientDTO.class)));
    }
}
