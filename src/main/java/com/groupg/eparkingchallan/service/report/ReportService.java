package com.groupg.eparkingchallan.service.report;

import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.entity.Report;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {
    ReportDto createReport(ReportDto reportDto) throws IllegalAccessException, CarNotFoundException, DriverNotFoundException;
    ReportDto getReport(Long id) throws Exception;
    Page<Report> pagination(int offset, int pageSize, String field);

}
