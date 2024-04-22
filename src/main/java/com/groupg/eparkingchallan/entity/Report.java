package com.groupg.eparkingchallan.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private String description;
    private String numberPlate;
    private String location;
    private String typeOfViolation;
    private LocalDateTime dayAndTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="car_id")
    @JsonIgnoreProperties("cars")
    private Car car;

}
