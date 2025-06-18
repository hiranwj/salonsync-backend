package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<Object> insertCustomerData(UserDto userDto);

    ResponseEntity<Object> loginCustomerData(UserDto userDto);
}
