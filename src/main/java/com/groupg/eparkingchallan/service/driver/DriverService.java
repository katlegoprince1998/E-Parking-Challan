package com.groupg.eparkingchallan.service.driver;

import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;

public interface DriverService {
    DriverDto createDriver(DriverDto driverDto);
    DriverDto findDriver(Long id) throws DriverNotFoundException;
    DriverDto getDriverByLicenseNumber(String licenseNumber) throws DriverNotFoundException;

}
