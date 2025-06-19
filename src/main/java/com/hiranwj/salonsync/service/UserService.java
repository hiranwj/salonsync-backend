package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.UserPasswordUpdateDto;
import com.hiranwj.salonsync.dto.UserRoleUpdateDto;
import com.hiranwj.salonsync.dto.UserUpdateDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> getUserProfileById(Integer userId);

    ResponseEntity<Object> updateUserProfile(Integer userId, UserUpdateDto userUpdateDto);

    ResponseEntity<Object> updateUserPassword(Integer userId, UserPasswordUpdateDto passwordUpdateDto);

    ResponseEntity<Object> updateUserRole(Integer userId, UserRoleUpdateDto userRoleUpdateDto);

    ResponseEntity<Object> deleteUserAccount(Integer userId);

    ResponseEntity<Object> getUsersByRole(String role);
}
