package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.FeedbackDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/feedback/stylist")
    public ResponseEntity<Object> getFeedbacksByStylistId(@Valid @RequestParam("id") Integer id) {
        ResponseEntity<Object> res = feedbackService.getFeedbacksByStylistId(id);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

}
