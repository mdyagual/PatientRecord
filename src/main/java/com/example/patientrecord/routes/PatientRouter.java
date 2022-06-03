package com.example.patientrecord.routes;

import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.usecases.*;
import com.mongodb.internal.connection.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
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
        //Function<Lo que recibe, lo que va a devolver>
        Function<PatientDTO, Mono<ServerResponse>> executeGuardar = patientDTO -> savePatientUseCase.apply(patientDTO)
                .flatMap(result -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result));

        return route(POST("/save/patient")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(PatientDTO.class).flatMap(executeGuardar));



    }
    //GET ALL PATIENTS
    @Bean
    public RouterFunction<ServerResponse> obtenerPacientesRouter(GetAllPatientsUseCase getAllPatientsUseCase){
        return route(GET("/all"), request -> ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(getAllPatientsUseCase.get(),PatientDTO.class)));
    }

    //GET PATIENT BY ID
    @Bean
    RouterFunction<ServerResponse> obtenerPacientePorIdRouter(GetPatientByIdUseCase getPatientByIdUseCase){
        return route(GET("/patient/{id}"),
                request -> {
                    return getPatientByIdUseCase.apply(request.pathVariable("id"))
                            .onErrorResume(t -> Mono.empty()) //Capture the error and return the Mono.empty() bc we are expecting later the use of flatMap
                            .flatMap(patientDTO -> ServerResponse.ok() //If error does not happend will execute
                            .contentType(MediaType.APPLICATION_JSON) //etc
                            .bodyValue(patientDTO)) //etc
                            //When the error happends we return an empty and then return the status that we wanted
                            .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build());
                            //.then(ServerResponse.status(HttpStatus.NOT_FOUND).build()); //Not properly use in this case bc we are expecting to do smth: call a function e.g

                });
                //.onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
        //.onErrorReturn(Mono.just(ServerResponse.status(HttpStatus.NO_CONTENT))));
    }

    //UPDATE PATIENT
    @Bean
    RouterFunction<ServerResponse> actualizarPacienteRouter(UpdatePatientUseCase updatePatientUseCase){

        return route(PUT("/update/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PatientDTO.class)
                            .flatMap(patientDTO -> updatePatientUseCase.apply(request.pathVariable("id"),patientDTO))
                            .flatMap(result -> result.getName()!=null
                            ? ServerResponse.status(HttpStatus.CREATED)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(result)
                            : ServerResponse.status(HttpStatus.NOT_FOUND)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(result)));
    }

    //BLOCK PATIENT
    @Bean
    public RouterFunction<ServerResponse> bloquearAtencionRouter(BlockAttendanceUseCase blockAttendanceUseCase){
        return route(PUT("/block/attention/{id}"),
                request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(blockAttendanceUseCase.apply(request.pathVariable("id")),String.class))
        );
    }

    //DELETE PATIENT BY ID
    @Bean
    RouterFunction<ServerResponse> eliminarPacienteRouter(DeletePatientUseCase deletePatientUseCase){
        return route(DELETE("/delete/patient/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
        .body(BodyInserters.fromPublisher(deletePatientUseCase.apply(request.pathVariable("id")),Void.class)));

    }

}
