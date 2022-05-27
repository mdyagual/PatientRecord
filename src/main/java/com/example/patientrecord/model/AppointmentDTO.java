package com.example.patientrecord.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {

    private String idAppointment = UUID.randomUUID().toString().substring(0, 10);
    private LocalDate date;
    private String hour;
    private String statusAppointment; /*ASISTIO, NO ASISTIO, CANCELADA, REAGENDADA*/
    private String doctor;
    private String patientId;

}
