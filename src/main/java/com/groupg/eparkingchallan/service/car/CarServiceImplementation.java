package com.groupg.eparkingchallan.service.car;

import com.groupg.eparkingchallan.dto.CarDto;
import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.entity.Car;
import com.groupg.eparkingchallan.entity.Driver;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import com.groupg.eparkingchallan.repository.CarRepository;
import com.groupg.eparkingchallan.repository.DriverRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImplementation implements CarService{
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public CarServiceImplementation(CarRepository carRepository, DriverRepository driverRepository){
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public CarDto addCar(CarDto carDto, DriverDto driverDto) throws DriverNotFoundException {
        // Check if carDto or driverDto is null
        if (carDto == null) {
            throw new IllegalArgumentException("CarDto is null.");
        }
        // Retrieve the Driver entity using the driverId from CarDto
        Long driverId = driverDto.getId();
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with id: " + driverId));
        // Create a new Car entity and associate it with the retrieved Driver
        Car car = Car.builder()
                .name(carDto.getName())
                .model(carDto.getModel())
                .numberPlate(carDto.getNumberPlate())
                .color(carDto.getColor())
                .driver(driver)  // Associate the driver entity with the car
                .build();
        // Save the car entity
        carRepository.save(car);
        // Copy properties from the saved car entity back to a new CarDto
        return CarDto.builder()
                .id(car.getId())
                .name(car.getName())
                .model(car.getModel())
                .numberPlate(car.getNumberPlate())
                .color(car.getColor())
                .driver(car.getDriver())
                .build();
    }


    @Override
    public CarDto getCar(Integer id) throws CarNotFoundException{
        Optional<Car> car = carRepository.findById(id);
        CarDto carDto = new CarDto();

        if(car.isPresent()){
            BeanUtils.copyProperties(car, carDto);
            return carDto;
        }else {
            throw new  CarNotFoundException("Car Not Found");
        }

    }

    @Override
    public List<CarDto> getAllCars(Driver driver) throws CarNotFoundException{
        List<Car> car = carRepository.findByDriver(driver);
        if (car.isEmpty()){
            throw new CarNotFoundException("Car Not Found");
        }
        return car.stream().map(
          this::convertToDto
        ).collect(Collectors.toList());
    }

    private CarDto convertToDto(Car car){
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setName(car.getName());
        carDto.setColor(car.getColor());
        carDto.setNumberPlate(car.getNumberPlate());

        return carDto;

    }
}
