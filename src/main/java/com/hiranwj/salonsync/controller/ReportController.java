package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/reports/summary")
    public ResponseEntity<Object> getSummaryReport() {
        ResponseEntity<Object> res = reportService.getSummaryReport();
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

}
