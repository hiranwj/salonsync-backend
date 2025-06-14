package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDtoResponse {
    private Integer id;

    private String customerName;

    private String contactNumber;

    private String serviceType;

    private String appointmentDate;

    private String appointmentTime;

    private Integer stylistId;

    private Integer createdBy;

    private Integer createdAt;
}
