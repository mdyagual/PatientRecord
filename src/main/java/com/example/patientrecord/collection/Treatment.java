package com.example.patientrecord.collection;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "patient")
public class Treatment {
    @Id
    private String idTreatment;
    private String diseaseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String treatmentStatus; /**/
}
