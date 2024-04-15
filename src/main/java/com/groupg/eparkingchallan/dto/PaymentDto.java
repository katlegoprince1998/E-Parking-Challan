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
public class PaymentDto {
    private Long id;
    private double amount;
    private String accountHolder;
    private String cvv;
    private String cardNumber;
    private String expiryDate;
    private Driver driver;
}
