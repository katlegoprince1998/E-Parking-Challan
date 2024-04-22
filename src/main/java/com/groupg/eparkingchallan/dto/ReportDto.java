package com.groupg.eparkingchallan.dto;

import com.groupg.eparkingchallan.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDto {
    private Long id;
    private double amount;
    private String description;
    private String numberPlate;
    private String location;
    private String typeOfViolation;
    private LocalDateTime dayAndTime;
    private Car car;
}
