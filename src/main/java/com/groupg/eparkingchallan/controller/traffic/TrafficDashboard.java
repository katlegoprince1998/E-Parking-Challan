package com.groupg.eparkingchallan.controller.traffic;

import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import com.groupg.eparkingchallan.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/")
@RequiredArgsConstructor
public class TrafficDashboard {
    private final ReportService reportService;

    @PostMapping("/report")
        public ResponseEntity<Object> createReport(
                @RequestBody ReportDto reportDto
                ) throws IllegalAccessException, DriverNotFoundException, CarNotFoundException {

              ReportDto reportDto1 = reportService.createReport(reportDto);
              return new ResponseEntity<>(reportDto1, HttpStatus.CREATED);


        }
}
