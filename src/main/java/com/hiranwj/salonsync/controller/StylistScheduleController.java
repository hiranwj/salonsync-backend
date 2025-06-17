package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.StylistScheduleDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.StylistScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
public class StylistScheduleController {

    @Autowired
    private StylistScheduleService scheduleService;

    @PostMapping(value = "/schedule")
    public ResponseEntity<Object> addSchedule(@Valid @RequestBody StylistScheduleDto scheduleDto) {
        ResponseEntity<Object> res = scheduleService.addSchedule(scheduleDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

    @GetMapping(value = "/schedule/stylist")
    public ResponseEntity<Object> getSchedulesByStylistId(@Valid @RequestParam("id") Integer id) {
        ResponseEntity<Object> res = scheduleService.getSchedulesByStylistId(id);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

}
