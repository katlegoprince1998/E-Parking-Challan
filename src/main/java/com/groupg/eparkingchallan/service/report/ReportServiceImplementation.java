package com.groupg.eparkingchallan.service.report;

import com.groupg.eparkingchallan.config.twilio.SmsPojo;
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
import com.groupg.eparkingchallan.service.twilio.SmsService;
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
    private final SmsService smsService;

    @Autowired
    public ReportServiceImplementation(ReportRepository reportRepository,
                                       UserRepository userRepository, CarRepository carRepository,
                                       DriverRepository driverRepository, SmsService smsService) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.smsService = smsService;
    }

    @Override
    public ReportDto createReport(ReportDto reportDto) throws CarNotFoundException, DriverNotFoundException {

        Car car = carRepository.findByNumberPlate(reportDto.getNumberPlate());
        if (car == null){
            throw new CarNotFoundException("Car was not found");
        }
        Driver driver = driverRepository.findById(car.getDriver().getId()).orElseThrow(()
                -> new DriverNotFoundException("Driver not found"));


        Report report = new Report();
        report.setDayAndTime(LocalDateTime.now());
        report.setAmount(reportDto.getAmount());
        report.setDescription(reportDto.getDescription());
        report.setLocation(reportDto.getLocation());
        report.setTypeOfViolation(reportDto.getTypeOfViolation());
        report.setNumberPlate(reportDto.getNumberPlate());
        report.setCar(car);
        report = reportRepository.save(report);
        SmsPojo smsPojo = new SmsPojo();
        String text = "Good day " + driver.getFirstname() + " " +
                driver.getLastname() + ", you have been charged  R" + reportDto.getAmount()
                + " for" + reportDto.getTypeOfViolation() + ". Please visit http://localhost:300 to pay or add your ticket " +
                "to your annual tax"
                ;
        smsPojo.setMessage(text);
        smsPojo.setSendTo(driver.getPhone());
        smsService.send(smsPojo);


        return ReportDto.builder()
                .dayAndTime(report.getDayAndTime())
                .amount(report.getAmount())
                .description(report.getDescription())
                .location(report.getLocation())
                .typeOfViolation(report.getTypeOfViolation())
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

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> searchViolations(String numberPlate, String location, String typeOfViolation) {
        // Implement search logic using repository
        return reportRepository.findAllByNumberPlateAndLocationAndTypeOfViolation(numberPlate, location, typeOfViolation);
    }

    @Override
    public List<Report> getReportsByDriverLicenseNumber(String licenseNumber) {
        return reportRepository.findAllByCar_Driver_LicenseNumber(licenseNumber);
    }
}