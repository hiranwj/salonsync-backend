package com.hiranwj.salonsync.service.impl;

import com.hiranwj.salonsync.dto.*;
import com.hiranwj.salonsync.model.User;
import com.hiranwj.salonsync.repository.UserRepository;
import com.hiranwj.salonsync.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public ResponseEntity<Object> updateUserRole(Integer userId, UserRoleUpdateDto userRoleUpdateDto) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            User user = userOptional.get();

            // Validate the role
            String newRole = userRoleUpdateDto.getRole().toUpperCase();
            if (!newRole.equals("ADMIN") && !newRole.equals("CUSTOMER") && !newRole.equals("STAFF")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role. Allowed roles: ADMIN, CUSTOMER, STAFF.");
            }

            user.setRole(newRole);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("User role updated successfully to: " + newRole);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deleteUserAccount(Integer userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            userRepository.deleteById(userId);

            return ResponseEntity.status(HttpStatus.OK).body("User account deleted successfully.");

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getUsersByRole(String role) {
        try {
            String roleUpper = role.toUpperCase();

            if (!roleUpper.equals("ADMIN") && !roleUpper.equals("CUSTOMER") && !roleUpper.equals("STAFF")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role. Allowed roles: ADMIN, CUSTOMER, STAFF.");
            }

            List<User> userList = userRepository.findByRole(roleUpper);

            if (userList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found for role: " + roleUpper);
            }

            List<UserResponseDto> userDtoList = userList.stream()
                    .map(user -> new UserResponseDto(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getContactNumber(),
                            user.getRole(),
                            user.getCreatedAt()
                    ))
                    .collect(Collectors.toList());


            return ResponseEntity.status(HttpStatus.OK).body(userDtoList);

        } catch (Exception e) {
            log.error("Ex. message: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
