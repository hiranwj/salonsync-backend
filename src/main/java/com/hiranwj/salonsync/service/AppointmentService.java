package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.AppointmentDto;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    ResponseEntity<Object> bookAppointment(AppointmentDto appointmentDto);
}
