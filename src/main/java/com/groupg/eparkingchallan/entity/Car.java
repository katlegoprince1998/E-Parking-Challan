package com.groupg.eparkingchallan.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String color;
    private String numberPlate;
    private String model;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="driver_id")
    @JsonIgnoreProperties("cars")
    private Driver driver;

}
