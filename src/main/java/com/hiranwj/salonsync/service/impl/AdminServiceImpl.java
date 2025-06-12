package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.AdminDto;
import com.hiranwj.salonsync.model.Admin;
import com.hiranwj.salonsync.model.util.JwtUtil;
import com.hiranwj.salonsync.repository.AdminRepository;
import com.hiranwj.salonsync.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<Object> insertAdminData(AdminDto adminDto) {

        try {
            Optional<Admin> existingAdmin = adminRepository.findByUsername(adminDto.getUsername());
            if (existingAdmin.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Admin with this Username already exists.");
            }
            Admin admin = new Admin();
            admin.setUsername(adminDto.getUsername());
            admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
            admin.setContactNumber(adminDto.getContactNumber());
            admin.setCreatedAt((int) (System.currentTimeMillis() / 1000));
            adminRepository.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin saved successfully");
        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> loginAdminData(AdminDto adminDto) {
        try {
            Optional<Admin> optionalAdmin = adminRepository.findByUsername(adminDto.getUsername());
            if (optionalAdmin.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }

            Admin admin = optionalAdmin.get();

            // Check if password matches
            if (!passwordEncoder.matches(adminDto.getPassword(), admin.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }

            // Generate token
            String token = jwtUtil.generateTokenForAdmin(admin);

            // Build response
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", admin.getUsername());
            response.put("contactNumber", admin.getContactNumber());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return new ResponseEntity<>("Login failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
