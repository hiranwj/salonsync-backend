package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.UserDto;
import com.hiranwj.salonsync.dto.UserPasswordUpdateDto;
import com.hiranwj.salonsync.dto.UserUpdateDto;
import com.hiranwj.salonsync.model.User;
import com.hiranwj.salonsync.repository.UserRepository;
import com.hiranwj.salonsync.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Object> getUserProfileById(Integer userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            User user = userOptional.get();

            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setContactNumber(user.getContactNumber());
            userDto.setRole(user.getRole());
            userDto.setCreatedAt(user.getCreatedAt());

            return ResponseEntity.status(HttpStatus.OK).body(userDto);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateUserProfile(Integer userId, UserUpdateDto userUpdateDto) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            User user = userOptional.get();

            user.setName(userUpdateDto.getName());
            user.setEmail(userUpdateDto.getEmail());
            user.setContactNumber(userUpdateDto.getContactNumber());

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("User profile updated successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateUserPassword(Integer userId, UserPasswordUpdateDto passwordUpdateDto) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            User user = userOptional.get();

            // Check if current password matches
            if (!passwordEncoder.matches(passwordUpdateDto.getCurrentPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password is incorrect.");
            }

            // Update password
            user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
