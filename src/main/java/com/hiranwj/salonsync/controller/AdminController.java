package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.AdminDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/salonsync")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/adminData")
    public ResponseEntity<Object> insertAdminData(@Valid @RequestBody AdminDto adminDto) {
        ResponseEntity<Object> res = adminService.insertAdminData(adminDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }
}
