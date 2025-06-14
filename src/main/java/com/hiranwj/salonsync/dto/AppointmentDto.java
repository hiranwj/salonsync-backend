package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private String customerName;
    private String contactNumber;
    private String serviceType;
    private String appointmentDate;
    private String appointmentTime;
    private Integer stylistId;
    private Integer createdBy;
}
