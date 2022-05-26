package com.example.patientrecord.collection;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "patient")
public class Patient {
    @Id
    private String id;

    private String idBussiness;

    private String name;

    private Integer age;

    private Boolean onTreatment;

    private String doctor;

    private String statusAssurance; /*ACTIVE - INACTIVE - DOES NOT HAVE*/

    private String statusAttendance; /*BLOCKED - ENABLED */





}
