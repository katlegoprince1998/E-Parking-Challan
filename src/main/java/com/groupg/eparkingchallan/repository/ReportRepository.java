package com.groupg.eparkingchallan.repository;

import com.groupg.eparkingchallan.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByNumberPlateAndLocationAndTypeOfViolation(
            String numberPlate, String location, String typeOfViolation
    );

    List<Report> findAllByCar_Driver_LicenseNumber(String licenseNumber);
}
