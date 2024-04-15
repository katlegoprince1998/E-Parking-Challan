package com.groupg.eparkingchallan.service.payment;
import com.groupg.eparkingchallan.dto.PaymentDto;
import com.groupg.eparkingchallan.dto.ReportDto;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto, ReportDto reportDto);
}
