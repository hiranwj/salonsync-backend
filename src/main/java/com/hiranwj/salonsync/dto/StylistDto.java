package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StylistDto {
    private Integer id;
    private String name;
    private String specialization;
    private String contactNumber;
    private String email;
    private String gender;
    private Integer createdBy;
}
