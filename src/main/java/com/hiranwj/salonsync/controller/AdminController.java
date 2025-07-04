package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.UserDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/adminData/signup")
    public ResponseEntity<Object> insertAdminData(@Valid @RequestBody UserDto userDto) {
        ResponseEntity<Object> res = adminService.insertAdminData(userDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

    @PostMapping(value = "/adminData/login")
    public ResponseEntity<Object> loginAdminData(@Valid @RequestBody UserDto userDto) {
        ResponseEntity<Object> res = adminService.loginAdminData(userDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }
}
