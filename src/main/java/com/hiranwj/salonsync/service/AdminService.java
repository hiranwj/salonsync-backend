package com.hiranwj.salonsync.service;

import com.hiranwj.salonsync.dto.AdminDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<Object> insertAdminData(AdminDto adminDto);

    ResponseEntity<Object> loginAdminData(AdminDto adminDto);
}
