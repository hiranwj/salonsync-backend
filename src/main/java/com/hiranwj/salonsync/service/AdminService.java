package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<Object> insertAdminData(UserDto userDto);

    ResponseEntity<Object> loginAdminData(UserDto userDto);
}
