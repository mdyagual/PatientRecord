package com.example.patientrecord.model;


import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDTO {
    private String id;

    private String idPatient = UUID.randomUUID().toString().substring(0, 10);

    private String name;

    private Integer age;

    private String statusAssurance; /*ACTIVE - INACTIVE - DOES NOT HAVE*/

    private String statusAttendance; /*BLOCKED - ENABLED */

    /*private List<AppointmentDTO> appointmentList;

    private List<TreatmentDTO> treatmentList;*/
}
