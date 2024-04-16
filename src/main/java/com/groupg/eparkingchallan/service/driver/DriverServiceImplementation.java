package com.groupg.eparkingchallan.service.driver;

import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.entity.Driver;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import com.groupg.eparkingchallan.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImplementation implements DriverService{
    private final DriverRepository driverRepository;

    @Override
    public DriverDto createDriver(DriverDto driverDto) {
        Driver driver = Driver
                .builder()
                .email(driverDto.getEmail())
                .phone(driverDto.getPhone())
                .address(driverDto.getAddress())
                .firstname(driverDto.getFirstname())
                .lastname(driverDto.getLastname())
                .licenseNumber(driverDto.getLicenseNumber())
                .build();
        driverRepository.save(driver);
        BeanUtils.copyProperties(driver, driverDto);
        driverDto.setId(driver.getId());
        return driverDto;
    }

    @Override
    public DriverDto findDriver(Long id) throws DriverNotFoundException {
        Optional<Driver> driverOptional = driverRepository.findById(id);

        if (driverOptional.isPresent()){
            Driver driver = driverOptional.get();
            DriverDto driverDto = new DriverDto();
            BeanUtils.copyProperties(driver, driverDto);
            return driverDto;
        }else {
            throw new DriverNotFoundException("Driver Not Found");
        }

    }

    @Override
    public DriverDto getDriverByLicenseNumber(String licenseNumber) throws DriverNotFoundException {
        DriverDto optionalDriver = driverRepository.findByLicenseNumber(licenseNumber);
        if (optionalDriver == null){
            throw  new DriverNotFoundException("Driver was not found");
        }
        DriverDto driverDto = new DriverDto();
        BeanUtils.copyProperties(optionalDriver, driverDto);
        return driverDto;
    }
}
