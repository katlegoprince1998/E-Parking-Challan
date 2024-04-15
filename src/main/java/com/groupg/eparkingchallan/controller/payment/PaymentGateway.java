package com.groupg.eparkingchallan.controller.payment;

import com.groupg.eparkingchallan.dto.PaymentDto;
import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.service.payment.PaymentService;
import com.groupg.eparkingchallan.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentGateway {

    private final PaymentService paymentService;
    private final ReportService reportService;

    @PostMapping("/pay/{reportId}")
    public ResponseEntity<Object> payTicket(@RequestBody PaymentDto paymentDto,
                                            @PathVariable Long reportId) {
        try {
            ReportDto reportDto = reportService.getReport(reportId);
            if (reportDto == null) {
                return new ResponseEntity<>("Report not found", HttpStatus.NOT_FOUND);
            }

            PaymentDto createdPaymentDto = paymentService.createPayment(paymentDto, reportDto);
            return new ResponseEntity<>(createdPaymentDto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle specific exceptions thrown during payment processing
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle generic exceptions
            return new ResponseEntity<>("Failed to process payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
