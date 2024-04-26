package com.groupg.eparkingchallan.repository;

import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByLicenseNumber(String licenseNumber);
}
