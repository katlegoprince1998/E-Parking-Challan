package com.groupg.eparkingchallan.service.payment;

import com.groupg.eparkingchallan.dto.PaymentDto;
import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.entity.Driver;
import com.groupg.eparkingchallan.entity.Payment;
import com.groupg.eparkingchallan.entity.Report;
import com.groupg.eparkingchallan.repository.DriverRepository;
import com.groupg.eparkingchallan.repository.PaymentRepository;
import com.groupg.eparkingchallan.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplementation implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReportRepository reportRepository;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto, ReportDto reportDto) {
        Report report = reportRepository.findById(reportDto.getId()).orElseThrow(()
                -> new IllegalArgumentException("Report not found"));
        Driver driver = report.getCar().getDriver();

        // Subtract payment amount from report
        double updatedAmount = report.getAmount() - paymentDto.getAmount();
        report.setAmount(updatedAmount);

        // Save updated report (if needed)
        reportRepository.save(report);

        // Create and save payment
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setCvv(paymentDto.getCvv());
        payment.setDriver(driver);
        payment.setExpiryDate(paymentDto.getExpiryDate());
        payment.setAccountHolder(paymentDto.getAccountHolder());
        payment.setCardNumber(paymentDto.getCardNumber());

        // Save payment to generate ID
        payment = paymentRepository.save(payment);

        BeanUtils.copyProperties(payment, paymentDto);
        // Set the generated payment ID in paymentDto
        paymentDto.setId(payment.getId());

        // Return populated PaymentDto
        return paymentDto;
    }
}
