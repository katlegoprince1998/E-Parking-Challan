package com.groupg.eparkingchallan.controller.driver;

import com.groupg.eparkingchallan.dto.CarDto;
import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import com.groupg.eparkingchallan.service.car.CarService;
import com.groupg.eparkingchallan.service.driver.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dashboard")
public class DriverDashboard {
    private final DriverService driverService;
    private final CarService carService;

    @PostMapping("/create/driver")
    public ResponseEntity<Object> createDriver(@RequestBody DriverDto driverDto){

        try {
            DriverDto driverDto1 = driverService.createDriver(driverDto);
            return new ResponseEntity<>(driverDto1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to create driver",
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/create/car/{driverId}")
    public ResponseEntity<Object> createCar(
            @RequestBody CarDto carDto, @PathVariable Long driverId
    ) {
        try {
            // Find the DriverDto using the driverId
            DriverDto driverDto = driverService.findDriver(driverId);

            // Add the car using carDto and retrieved driverDto
            CarDto savedCarDto = carService.addCar(carDto, driverDto);

            return new ResponseEntity<>(savedCarDto, HttpStatus.CREATED);
        } catch (DriverNotFoundException e) {
            return new ResponseEntity<>("Driver not found with id: " + driverId, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get/driver/{id}")
    public ResponseEntity<Object> getDriverById(@PathVariable Long id) throws DriverNotFoundException {
        DriverDto driverDto = driverService.findDriver(id);
        return new ResponseEntity<>(driverDto, HttpStatus.OK);
    }
    @GetMapping("/getDriver/{licenseNumber}")
    public DriverDto getDriverByLicenseNumber(@PathVariable String licenseNumber) throws DriverNotFoundException {
        DriverDto optionalDriver = driverService.getDriverByLicenseNumber(licenseNumber);
        if (optionalDriver == null) {
            throw new DriverNotFoundException("Driver not found with license number: " + licenseNumber);
        }
        return optionalDriver;
    }
}
