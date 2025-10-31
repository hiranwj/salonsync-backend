package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private String customerName;
    private String contactNumber;
    private List<String> serviceType;
    private String stylistGender;
    private String appointmentDate;
    private String appointmentTime;
    private Integer stylistId;
    private Integer createdBy;
    private String note;
}
