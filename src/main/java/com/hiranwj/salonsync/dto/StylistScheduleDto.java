package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StylistScheduleDto {
    private Integer stylistId;
    private String scheduleDate;
    private String startTime;
    private String endTime;
    private Integer createdBy;
}
