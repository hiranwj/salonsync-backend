package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.StylistDto;
import org.springframework.http.ResponseEntity;

public interface StylistService {
    ResponseEntity<Object> insertStylistData(StylistDto stylistDto);

    ResponseEntity<Object> getAllStylists();

    ResponseEntity<Object> getStylistById(Integer id);
}
