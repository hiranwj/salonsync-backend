package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<Object> insertCustomerData(CustomerDto customerDto);
}
