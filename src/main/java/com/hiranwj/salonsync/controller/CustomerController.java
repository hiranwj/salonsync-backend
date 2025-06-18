package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.UserDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customerData/signup")
    public ResponseEntity<Object> insertCustomerData(@Valid @RequestBody UserDto userDto) {
        ResponseEntity<Object> res = customerService.insertCustomerData(userDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

    @PostMapping(value = "/customerData/login")
    public ResponseEntity<Object> loginCustomerData(@Valid @RequestBody UserDto userDto) {
        ResponseEntity<Object> res = customerService.loginCustomerData(userDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }
}
