package com.groupg.eparkingchallan.service.report;

import com.groupg.eparkingchallan.dto.DriverDto;
import com.groupg.eparkingchallan.dto.ReportDto;
import com.groupg.eparkingchallan.entity.Car;
import com.groupg.eparkingchallan.entity.Driver;
import com.groupg.eparkingchallan.entity.Report;
import com.groupg.eparkingchallan.entity.User;
import com.groupg.eparkingchallan.exception.CarNotFoundException;
import com.groupg.eparkingchallan.exception.DriverNotFoundException;
import com.groupg.eparkingchallan.repository.CarRepository;
import com.groupg.eparkingchallan.repository.DriverRepository;
import com.groupg.eparkingchallan.repository.ReportRepository;
import com.groupg.eparkingchallan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImplementation implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @Override
    public ReportDto createReport(ReportDto reportDto) throws CarNotFoundException, DriverNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new IllegalStateException("User authentication not available or user not authenticated");
        }
        String username = userDetails.getUsername();

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new IllegalStateException("User not found for username: " + username);
        }
        Car car = carRepository.findByNumberPlate(reportDto.getNumberPlate());
        if (car == null){
            throw new CarNotFoundException("Car was not found");
        }
        Driver driver = driverRepository.findById(car.getDriver().getId()).orElseThrow(()
                -> new DriverNotFoundException("Driver not found"));


        Report report = new Report();
        report.setDayAndTime(LocalDateTime.now());
        report.setImage(reportDto.getImage());
        report.setNumberPlate(reportDto.getNumberPlate());
        report.setUser(user);
        report.setCar(car);

        report = reportRepository.save(report);


        return ReportDto.builder()
                .dayAndTime(report.getDayAndTime())
                .image(report.getImage())
                .id(report.getId())
                .numberPlate(report.getNumberPlate())
                .car(report.getCar())
                .build();
    }

    @Override
    public List<ReportDto> getReport(DriverDto driverDto) {
        return List.of();
    }
    @Override
    public Page<ReportDto> pagination(int offset, int pageSize, String field) {
        return null;
    }


}
