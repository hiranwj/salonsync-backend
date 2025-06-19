package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleUpdateDto {
    @NotBlank(message = "Role is required")
    private String role; // Example: ADMIN, CUSTOMER, STAFF
}
