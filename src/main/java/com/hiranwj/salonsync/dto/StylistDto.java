package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StylistDto {
    private String firstName;
    private String lastName;
    private String specialization;
    private String contactNumber;
    private String email;
    private Integer createdBy;
}
