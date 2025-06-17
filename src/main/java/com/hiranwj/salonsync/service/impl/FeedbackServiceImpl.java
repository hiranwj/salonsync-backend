package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.FeedbackDto;
import com.hiranwj.salonsync.model.Feedback;
import com.hiranwj.salonsync.repository.FeedbackRepository;
import com.hiranwj.salonsync.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public ResponseEntity<Object> submitFeedback(FeedbackDto feedbackDto) {
        try {
            Feedback feedback = new Feedback();
            feedback.setStylistId(feedbackDto.getStylistId());
            feedback.setFeedbackText(feedbackDto.getFeedbackText());
            feedback.setRating(feedbackDto.getRating());
            feedback.setCreatedBy(feedbackDto.getCreatedBy());
            feedback.setCreatedAt((int) (System.currentTimeMillis() / 1000));

            feedbackRepository.save(feedback);

            return ResponseEntity.status(HttpStatus.CREATED).body("Feedback submitted successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
