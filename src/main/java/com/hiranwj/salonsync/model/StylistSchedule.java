package com.hiranwj.salonsync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "stylist_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StylistSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer stylistId;

    @NotBlank(message = "Schedule date is mandatory")
    private String scheduleDate;

    @NotBlank(message = "Start time is mandatory")
    private String startTime;

    @NotBlank(message = "End time is mandatory")
    private String endTime;

    private Integer createdBy;

    private Integer createdAt;
}
