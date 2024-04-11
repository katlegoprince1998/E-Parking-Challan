package com.groupg.eparkingchallan.repository;


import com.groupg.eparkingchallan.entity.Car;
import com.groupg.eparkingchallan.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByDriver(Driver driver);

}
