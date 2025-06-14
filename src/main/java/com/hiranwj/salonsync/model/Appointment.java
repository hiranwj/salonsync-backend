package com.hiranwj.salonsync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Customer name is mandatory")
    private String customerName;

    @NotBlank(message = "Contact number is mandatory")
    private String contactNumber;

    @NotBlank(message = "Service type is mandatory")
    private String serviceType;

    @NotBlank(message = "Appointment date is mandatory")
    private String appointmentDate;  // Store as String (YYYY-MM-DD) or use LocalDate

    @NotBlank(message = "Appointment time is mandatory")
    private String appointmentTime;  // Store as String (HH:mm) or use LocalTime

    private Integer stylistId;

    private Integer createdBy;

    private Integer createdAt;
}
