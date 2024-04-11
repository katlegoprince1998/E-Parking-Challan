package com.groupg.eparkingchallan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DriverDto {
   private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String licenseNumber;
}
