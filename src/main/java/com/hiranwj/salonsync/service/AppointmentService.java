package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.AppointmentDto;
import com.hiranwj.salonsync.dto.AppointmentStatusDto;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    ResponseEntity<Object> bookAppointment(AppointmentDto appointmentDto);

    ResponseEntity<Object> getAllAppointments();

    ResponseEntity<Object> updateAppointmentStatus(Integer id, AppointmentStatusDto statusDto);

    ResponseEntity<Object> deleteAppointment(Integer id);
}
