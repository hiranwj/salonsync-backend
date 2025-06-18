package com.hiranwj.salonsync.service;

import org.springframework.http.ResponseEntity;

public interface ReportService {
    ResponseEntity<Object> getSummaryReport();
}
