package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.UserUpdateDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> getUserProfileById(Integer userId);

    ResponseEntity<Object> updateUserProfile(Integer userId, UserUpdateDto userUpdateDto);
}
