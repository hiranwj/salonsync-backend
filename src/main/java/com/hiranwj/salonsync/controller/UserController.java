package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<Object> getUserProfile(@Valid @RequestParam("id") Integer id) {
        ResponseEntity<Object> res = userService.getUserProfileById(id);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }

}
