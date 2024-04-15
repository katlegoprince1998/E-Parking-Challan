package com.groupg.eparkingchallan.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private String accountHolder;
    private String cvv;
    private String cardNumber;
    private String expiryDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="driver_id")
    @JsonIgnoreProperties("drivers")
    private Driver driver;
}
