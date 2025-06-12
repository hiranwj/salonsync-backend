package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.StylistDto;
import com.hiranwj.salonsync.model.Stylist;
import com.hiranwj.salonsync.repository.StylistRepository;
import com.hiranwj.salonsync.service.StylistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StylistServiceImpl implements StylistService {
    @Autowired
    private StylistRepository stylistRepository;

    @Override
    public ResponseEntity<Object> insertStylistData(StylistDto stylistDto) {
        try {
            Optional<Stylist> existingStylist = stylistRepository.findByEmail(stylistDto.getEmail());
            if (existingStylist.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Stylist with this email already exists.");
            }

            Stylist stylist = new Stylist();
            stylist.setFirstName(stylistDto.getFirstName());
            stylist.setLastName(stylistDto.getLastName());
            stylist.setSpecialization(stylistDto.getSpecialization());
            stylist.setContactNumber(stylistDto.getContactNumber());
            stylist.setEmail(stylistDto.getEmail());
            stylist.setCreatedAt((int) (System.currentTimeMillis() / 1000));

            stylistRepository.save(stylist);

            return ResponseEntity.status(HttpStatus.CREATED).body("Stylist added successfully");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
