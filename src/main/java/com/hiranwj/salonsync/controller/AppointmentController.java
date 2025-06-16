package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.AppointmentDto;
import com.hiranwj.salonsync.dto.AppointmentStatusDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/appointment")
    public ResponseEntity<Object> bookAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        ResponseEntity<Object> res = appointmentService.bookAppointment(appointmentDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

    @GetMapping(value = "/appointments")
    public ResponseEntity<Object> getAllAppointments() {
        ResponseEntity<Object> res = appointmentService.getAllAppointments();
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

    @PutMapping(value = "/appointment")
    public ResponseEntity<Object> updateAppointmentStatus(@Valid @RequestParam("id") Integer id, @RequestBody AppointmentStatusDto statusDto) {
        ResponseEntity<Object> res = appointmentService.updateAppointmentStatus(id, statusDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }
}
