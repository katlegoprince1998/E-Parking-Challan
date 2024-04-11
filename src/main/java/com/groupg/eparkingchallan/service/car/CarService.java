package com.groupg.eparkingchallan.service.car;

import com.groupg.eparkingchallan.dto.CarDto;
import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.entity.Driver;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;

import java.util.List;

public interface CarService {
    CarDto addCar(CarDto carDto, DriverDto driverDto) throws DriverNotFoundException;
    CarDto getCar(Integer id) throws CarNotFoundException;
    List<CarDto> getAllCars(Driver driver) throws CarNotFoundException;
}
