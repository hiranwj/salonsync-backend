package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.StylistDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.StylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
public class StylistController {

    @Autowired
    private StylistService stylistService;

    @PostMapping(value = "/stylistData")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> insertStylistData(@Valid @RequestBody StylistDto stylistDto) {
        ResponseEntity<Object> res = stylistService.insertStylistData(stylistDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

    @GetMapping(value = "/stylistData")
    public ResponseEntity<Object> getAllStylists() {
        ResponseEntity<Object> res = stylistService.getAllStylists();
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }
}
