package com.example.patientrecord.controller;

import com.example.patientrecord.model.PatientDTO;
import com.example.patientrecord.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    private PatientService patientService;

    //GET
    @GetMapping("/all")
    private Flux<PatientDTO> obtenerPacientes(){
        return this.patientService.findAllPatients();
    }

    @GetMapping("/patient/{id}")
    private Mono<PatientDTO> obtenerPacientePorId(@PathVariable("id") String id){
        return this.patientService.findPatientById(id);
    }

    /*@GetMapping("/patients/doctor/{name}")
    private Flux<PatientDTO> obtenerPacientesPorDoctor(@PathVariable("name") String name){
        return this.patientService.getPatientByDoctor(name);
    }*/

    //POST
    @PostMapping("/save/patient")
    private Mono<PatientDTO> guardarPaciente(@RequestBody PatientDTO p){
        return this.patientService.savePatient(p);
    }

    //PUT
    @PutMapping("/update/{id}")
    private Mono<PatientDTO> actualizarPaciente(@PathVariable("id") String id, @RequestBody PatientDTO p){
        return this.patientService.updatePatient(id,p);
    }

    @PutMapping("/block/attention/{id}")
    private Mono<PatientDTO> bloquearAtencion(@PathVariable("id") String id){
        return this.patientService.blockAttendance(id);
    }

    @PutMapping("/unblock/attention/{id}")
    private Mono<PatientDTO> desbloquearAtencion(@PathVariable("id") String id){
        return this.patientService.unblockAttendance(id);
    }

    @PutMapping("/update/assurance/{id}") //?status=...
    private Mono<PatientDTO> actualizarEstadoSeguro(@PathVariable("id") String id, @RequestParam String status){
        return this.patientService.updateAssuranceStatus(id,status);
    }
    /*@PutMapping("/update/doctor/{id}") //?name=....
    private Mono<PatientDTO> actualizarDoctor(String id, @RequestParam String name){
        return this.patientService.updateDoctor(id,name);
    }*/

    //DELETE
    @DeleteMapping("/delete/patient/{id}")
    private Mono<Void> eliminarPaciente(@PathVariable("id")  String id){
        return this.patientService.deletePatient(id);
    }

    @DeleteMapping("/delete/all/patient/")
    private Mono<Void> eliminarPacientes(){
        return this.patientService.deleteAllPatients();
    }

}
