package com.hiranwj.salonsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSummaryDto {
    private Long totalAppointments;
    private Long totalFeedbacks;
    private Double averageStylistRating;
}
