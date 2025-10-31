package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StylistDto {
    private Integer id;
    private String name;
    private List<String> specialization;
    private String contactNumber;
    private String email;
    private String gender;
    private Integer createdBy;
}
