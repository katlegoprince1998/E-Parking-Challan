package com.groupg.eparkingchallan.service.report;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImplementation implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;


    @Autowired
    public ReportServiceImplementation(ReportRepository reportRepository,
                                       UserRepository userRepository, CarRepository carRepository,
                                       DriverRepository driverRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;

    }

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
        report.setAmount(reportDto.getAmount());
        report.setDescription(reportDto.getDescription());
        report.setNumberPlate(reportDto.getNumberPlate());
        report.setUser(user);
        report.setCar(car);

        report = reportRepository.save(report);

        return ReportDto.builder()
                .dayAndTime(report.getDayAndTime())
                .image(report.getImage())
                .amount(report.getAmount())
                .description(report.getDescription())
                .id(report.getId())
                .numberPlate(report.getNumberPlate())
                .car(report.getCar())
                .build();
    }

    @Override
    public ReportDto getReport(Long id) throws Exception {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()){
            Report report = optionalReport.get();
            ReportDto reportDto = new ReportDto();
            BeanUtils.copyProperties(report,reportDto );
            return reportDto;
        }else {
            throw new Exception("Report was not found");
        }
    }
    @Override
    public Page<Report> pagination(int offset, int pageSize, String field) {
        if ("defaultValue".equals(field)){
            return reportRepository.findAll(
                    PageRequest.of(offset, pageSize)
            );
        }
        return reportRepository.findAll(
                PageRequest.of(offset, pageSize).withSort(Sort.by(field))
        );
    }
}

