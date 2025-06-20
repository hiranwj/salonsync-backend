package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.UserDto;
import com.hiranwj.salonsync.model.User;
import com.hiranwj.salonsync.model.util.JwtUtil;
import com.hiranwj.salonsync.repository.UserRepository;
import com.hiranwj.salonsync.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<Object> insertAdminData(UserDto userDto) {

        try {
            Optional<User> existingAdmin = userRepository.findByEmail(userDto.getEmail());
            if (existingAdmin.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Admin with this email already exists.");
            }
            User user = new User();
            user.setName(userDto.getName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setContactNumber(userDto.getContactNumber());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            user.setCreatedAt((int) (System.currentTimeMillis() / 1000));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin saved successfully");
        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> loginAdminData(UserDto userDto) {
        try {
            Optional<User> optionalAdmin = userRepository.findByEmail(userDto.getEmail());
            if (optionalAdmin.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }

            User user = optionalAdmin.get();

            // Check if password matches
            if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }

            // Generate token
            String token = jwtUtil.generateTokenForAdmin(user);

            // Build response
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("token", token);
            response.put("email", user.getEmail());
            response.put("contactNumber", user.getContactNumber());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return new ResponseEntity<>("Login failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
