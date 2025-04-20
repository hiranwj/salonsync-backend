package com.hiranwj.salonsync.model.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
//        map.put("status", status.value());
        if(status.equals(HttpStatus.OK)){
            map.put("Data", responseObj);
        }else {
            map.put("Data", null);
            map.put("Message", responseObj);
        }
        return new ResponseEntity<>(map,status);
    }
}
