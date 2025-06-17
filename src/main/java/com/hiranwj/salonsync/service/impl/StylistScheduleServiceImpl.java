package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.StylistScheduleDto;
import com.hiranwj.salonsync.model.StylistSchedule;
import com.hiranwj.salonsync.repository.StylistScheduleRepository;
import com.hiranwj.salonsync.service.StylistScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StylistScheduleServiceImpl implements StylistScheduleService {
    @Autowired
    private StylistScheduleRepository scheduleRepository;

    @Override
    public ResponseEntity<Object> addSchedule(StylistScheduleDto scheduleDto) {
        try {
            StylistSchedule schedule = new StylistSchedule();
            schedule.setStylistId(scheduleDto.getStylistId());
            schedule.setScheduleDate(scheduleDto.getScheduleDate());
            schedule.setStartTime(scheduleDto.getStartTime());
            schedule.setEndTime(scheduleDto.getEndTime());
            schedule.setCreatedBy(scheduleDto.getCreatedBy());
            schedule.setCreatedAt((int) (System.currentTimeMillis() / 1000));

            scheduleRepository.save(schedule);

            return ResponseEntity.status(HttpStatus.CREATED).body("Stylist schedule added successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getSchedulesByStylistId(Integer stylistId) {
        try {
            List<StylistSchedule> scheduleList = scheduleRepository.findByStylistId(stylistId);

            if (scheduleList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No schedules found for this stylist.");
            }

            List<StylistScheduleDto> scheduleDtoList = scheduleList.stream()
                    .map(schedule -> new StylistScheduleDto(
                            schedule.getStylistId(),
                            schedule.getScheduleDate(),
                            schedule.getStartTime(),
                            schedule.getEndTime(),
                            schedule.getCreatedBy()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(scheduleDtoList);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
