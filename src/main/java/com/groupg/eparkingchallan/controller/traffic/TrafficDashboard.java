package com.groupg.eparkingchallan.controller.traffic;

import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.entity.Report;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import com.groupg.eparkingchallan.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
