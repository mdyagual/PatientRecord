package com.example.patientrecord.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
public class TreatmentDTO {
    private String idTreatment;
    private String diseaseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String treatmentStatus; /*IN PROGRESS, COMPLETED, SUSPENDED*/
    private String patientId;
}
