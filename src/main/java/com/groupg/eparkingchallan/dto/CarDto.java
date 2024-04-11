package com.groupg.eparkingchallan.dto;

import com.groupg.eparkingchallan.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private Integer id;
    private String name;
    private String color;
    private String numberPlate;
    private String model;
    private Driver driver;
}
