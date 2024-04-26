package com.groupg.eparkingchallan.service.driver;

import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DriverService {
    DriverDto createDriver(DriverDto driverDto);
    DriverDto findDriver(Long id) throws DriverNotFoundException;
    DriverDto getDriverByLicenseNumber(String licenseNumber) throws DriverNotFoundException;

}
