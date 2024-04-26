package com.groupg.eparkingchallan.controller.traffic;

import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.entity.Driver;
import com.groupg.eparkingchallan.entity.Report;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import com.groupg.eparkingchallan.service.driver.DriverService;
import com.groupg.eparkingchallan.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dashboard/")
@RequiredArgsConstructor
public class TrafficDashboard {
    private final ReportService reportService;
    private final DriverService driverService;

    @PostMapping("/report")
    public ResponseEntity<Object> createReport(@RequestBody ReportDto reportDto) {
        try {
            ReportDto createdReport = reportService.createReport(reportDto);
            return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
        } catch (CarNotFoundException | DriverNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/report/{id}")
    public ResponseEntity<Object> getReport(@PathVariable Long id){
        try {
            ReportDto reportDto = reportService.getReport(id);
            return new ResponseEntity<>(reportDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<Report>> pagination(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @RequestParam(required = false, defaultValue = "defaultValue") String field) {
        Page<Report> reports= reportService.pagination(offset, pageSize, field);

        if (reports.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(reports);
        }
    }
    @GetMapping("/violations")
    public List<Report> violations(){
        return reportService.getAllReports();
    }

    @GetMapping("/search")
    public List<Report> searchViolations(
            @RequestParam(required = false) String numberPlate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String typeOfViolation
    ) {
        return reportService.searchViolations(numberPlate, location, typeOfViolation);
    }

    @GetMapping("/reports/{licenseNumber}")
    public ResponseEntity<List<Report>> getReportsByDriverLicenseNumber(@PathVariable String licenseNumber) {
        List<Report> reports = reportService.getReportsByDriverLicenseNumber(licenseNumber);
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}