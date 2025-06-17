package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.StylistScheduleDto;
import org.springframework.http.ResponseEntity;

public interface StylistScheduleService {
    ResponseEntity<Object> addSchedule(StylistScheduleDto scheduleDto);
}
