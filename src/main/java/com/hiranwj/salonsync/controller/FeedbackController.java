package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.FeedbackDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "/feedback")
    public ResponseEntity<Object> submitFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        ResponseEntity<Object> res = feedbackService.submitFeedback(feedbackDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }
}
