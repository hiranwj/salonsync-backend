package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private String role;
    private Integer createdAt;
}
