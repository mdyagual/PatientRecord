package com.example.patientrecord.collection;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "patient")
public class Appointment {
    @Id
    private String idAppointment;
    private LocalDate date;
    private String hour;
    private String statusAppointment; /*ASISTIO, NO ASISTIO, CANCELADA, REAGENDADA*/
    private String doctor;
    private String patientId;



}
