package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.FeedbackDto;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {
    ResponseEntity<Object> submitFeedback(FeedbackDto feedbackDto);

    ResponseEntity<Object> getFeedbacksByStylistId(Integer id);
}
