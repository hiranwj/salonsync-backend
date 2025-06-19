package com.hiranwj.salonsync.controller;

import com.hiranwj.salonsync.dto.UserUpdateDto;
import com.hiranwj.salonsync.model.util.ResponseHandler;
import com.hiranwj.salonsync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/users")
    public ResponseEntity<Object> updateUserProfile(@Valid @RequestParam("id") Integer id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        ResponseEntity<Object> res = userService.updateUserProfile(id, userUpdateDto);
        return ResponseHandler.generateResponse(res.getStatusCode(), res.getBody());
    }


}
