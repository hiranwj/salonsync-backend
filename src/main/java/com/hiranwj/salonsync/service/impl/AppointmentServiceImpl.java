package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.AppointmentDto;
import com.hiranwj.salonsync.dto.AppointmentDtoResponse;
import com.hiranwj.salonsync.dto.AppointmentStatusDto;
import com.hiranwj.salonsync.model.Appointment;
import com.hiranwj.salonsync.repository.AppointmentRepository;
import com.hiranwj.salonsync.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<Object> bookAppointment(AppointmentDto appointmentDto) {
        try {
            // Check if this stylist is already booked at this time
            boolean isBooked = appointmentRepository.existsByStylistIdAndAppointmentDateAndAppointmentTime(
                    appointmentDto.getStylistId(),
                    appointmentDto.getAppointmentDate(),
                    appointmentDto.getAppointmentTime()
            );

            if (isBooked) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("This stylist is already booked for the selected time slot.");
            }

            Appointment appointment = new Appointment();

            appointment.setCustomerName(appointmentDto.getCustomerName());
            appointment.setContactNumber(appointmentDto.getContactNumber());
            appointment.setServiceType(appointmentDto.getServiceType());
            appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
            appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
            appointment.setStylistId(appointmentDto.getStylistId()); // Set stylist
            appointment.setCreatedBy(appointmentDto.getCreatedBy());
            appointment.setCreatedAt((int) (System.currentTimeMillis() / 1000));

            appointmentRepository.save(appointment);

            return ResponseEntity.status(HttpStatus.CREATED).body("Appointment booked successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getAllAppointments() {
        try {
            List<Appointment> appointmentList = appointmentRepository.findAll();

            if (appointmentList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointments found.");
            }

            List<AppointmentDtoResponse> appointmentDtoList = appointmentList.stream()
                    .map(appointment -> new AppointmentDtoResponse(
                            appointment.getId(),
                            appointment.getCustomerName(),
                            appointment.getContactNumber(),
                            appointment.getServiceType(),
                            appointment.getAppointmentDate(),
                            appointment.getAppointmentTime(),
                            appointment.getStylistId(),
                            appointment.getCreatedBy(),
                            appointment.getCreatedAt()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(appointmentDtoList);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateAppointmentStatus(Integer id, AppointmentStatusDto statusDto) {
        try {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

            if (!optionalAppointment.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
            }

            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(statusDto.getStatus());

            appointmentRepository.save(appointment);

            return ResponseEntity.status(HttpStatus.OK).body("Appointment status updated successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deleteAppointment(Integer id) {
        try {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

            if (!optionalAppointment.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
            }

            appointmentRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("Appointment deleted successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getAppointmentsByUserId(Integer id) {
        try {
            List<Appointment> appointmentList = appointmentRepository.findByCreatedBy(id);

            if (appointmentList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointments found for the user.");
            }

            List<AppointmentDtoResponse> appointmentDtoList = appointmentList.stream()
                    .map(appointment -> new AppointmentDtoResponse(
                            appointment.getId(),
                            appointment.getCustomerName(),
                            appointment.getContactNumber(),
                            appointment.getServiceType(),
                            appointment.getAppointmentDate(),
                            appointment.getAppointmentTime(),
                            appointment.getStylistId(),
                            appointment.getCreatedBy(),
                            appointment.getCreatedAt()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(appointmentDtoList);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
