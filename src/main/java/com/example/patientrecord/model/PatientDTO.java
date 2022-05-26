package com.example.patientrecord.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {
    private String id;

    private String idBussiness = UUID.randomUUID().toString().substring(0, 10);

    private String name;

    private Integer age;

    private Boolean onTreatment;

    private String doctor;

    private String statusAssurance; /*ACTIVE - INACTIVE - DOES NOT HAVE*/

    private String statusAttendance; /*BLOCKED - ENABLED */
}
