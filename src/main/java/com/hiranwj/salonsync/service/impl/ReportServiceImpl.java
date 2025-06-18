package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.ReportSummaryDto;
import com.hiranwj.salonsync.repository.AppointmentRepository;
import com.hiranwj.salonsync.repository.FeedbackRepository;
import com.hiranwj.salonsync.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public ResponseEntity<Object> getSummaryReport() {
        try {
            Long totalAppointments = appointmentRepository.count();
            Long totalFeedbacks = feedbackRepository.count();

            Double averageRating = feedbackRepository.findAverageRating();
            if (averageRating == null) {
                averageRating = 0.0;
            }

            ReportSummaryDto reportSummary = new ReportSummaryDto(totalAppointments, totalFeedbacks, averageRating);

            return ResponseEntity.status(HttpStatus.OK).body(reportSummary);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
