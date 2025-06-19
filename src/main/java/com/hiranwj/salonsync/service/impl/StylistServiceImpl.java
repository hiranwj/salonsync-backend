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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            stylist.setName(stylistDto.getName());
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

    @Override
    public ResponseEntity<Object> getAllStylists() {
        try {
            List<Stylist> stylistList = stylistRepository.findAll();

            if (stylistList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No stylists found.");
            }

            List<StylistDto> stylistDtoList = stylistList.stream()
                    .map(stylist -> new StylistDto(
                            stylist.getName(),
                            stylist.getSpecialization(),
                            stylist.getContactNumber(),
                            stylist.getEmail(),
                            stylist.getCreatedAt()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(stylistDtoList);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getStylistById(Integer id) {
        try {
            Optional<Stylist> optionalStylist = stylistRepository.findById(id);

            if (!optionalStylist.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stylist not found with ID: " + id);
            }

            Stylist stylist = optionalStylist.get();
            StylistDto stylistDto = new StylistDto(
                    stylist.getName(),
                    stylist.getSpecialization(),
                    stylist.getContactNumber(),
                    stylist.getEmail(),
                    stylist.getCreatedAt()
            );

            return ResponseEntity.status(HttpStatus.OK).body(stylistDto);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateStylistById(Integer id, StylistDto stylistDto) {
        try {
            Optional<Stylist> optionalStylist = stylistRepository.findById(id);

            if (!optionalStylist.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stylist not found with ID: " + id);
            }

            Stylist existingStylist = optionalStylist.get();

            existingStylist.setName(stylistDto.getName());
            existingStylist.setSpecialization(stylistDto.getSpecialization());
            existingStylist.setContactNumber(stylistDto.getContactNumber());
            existingStylist.setEmail(stylistDto.getEmail());

            stylistRepository.save(existingStylist);

            StylistDto updatedStylistDto = new StylistDto(
                    existingStylist.getName(),
                    existingStylist.getSpecialization(),
                    existingStylist.getContactNumber(),
                    existingStylist.getEmail(),
                    existingStylist.getCreatedAt() // This will return the originally createdBy value
            );

            return ResponseEntity.status(HttpStatus.OK).body(updatedStylistDto);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
