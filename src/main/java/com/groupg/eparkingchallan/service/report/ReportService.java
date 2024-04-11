package com.groupg.eparkingchallan.service.report;

import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.dto.UserDto;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {
    ReportDto createReport(ReportDto reportDto) throws IllegalAccessException, CarNotFoundException, DriverNotFoundException;
    List<ReportDto> getReport(DriverDto driverDto);
    Page<ReportDto> pagination(int offset, int pageSize, String field);

}
