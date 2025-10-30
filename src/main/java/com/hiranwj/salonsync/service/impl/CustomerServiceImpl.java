package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.UserDto;
import com.hiranwj.salonsync.model.User;
import com.hiranwj.salonsync.model.util.JwtUtil;
import com.hiranwj.salonsync.repository.UserRepository;
import com.hiranwj.salonsync.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<Object> insertCustomerData(UserDto userDto) {
        try {
            Optional<User> existingCustomer = userRepository.findByContactNumber(userDto.getContactNumber());
            if (existingCustomer.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer with this contact number already exists.");
            }
            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(userDto.getRole());
            user.setContactNumber(userDto.getContactNumber());
            user.setCreatedAt((int) (System.currentTimeMillis() / 1000));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer saved successfully");
        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> loginCustomerData(UserDto userDto) {
        try {
            Optional<User> optionalCustomer = userRepository.findByEmail(userDto.getEmail());
            if (optionalCustomer.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }

            User user = optionalCustomer.get();

            // Check if password matches
            if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }

            // Generate token
            String token = jwtUtil.generateTokenForCustomer(user);

            // Build response
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("token", token);
            response.put("email", user.getEmail());
            response.put("name", user.getName());
            response.put("contactNumber", user.getContactNumber());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return new ResponseEntity<>("Login failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
