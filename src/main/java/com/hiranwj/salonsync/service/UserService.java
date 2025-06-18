package com.hiranwj.salonsync.service;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> getUserProfileById(Integer userId);
}
