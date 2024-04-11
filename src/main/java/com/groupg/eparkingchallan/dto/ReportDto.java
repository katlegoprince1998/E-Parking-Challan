package com.groupg.eparkingchallan.dto;

import com.groupg.eparkingchallan.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDto {
    private Long id;
    private String image;
    private String numberPlate;
    private LocalDateTime dayAndTime;
    private Car car;
}
