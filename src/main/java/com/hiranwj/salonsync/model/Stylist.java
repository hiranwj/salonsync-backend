package com.hiranwj.salonsync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "stylist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "First name is mandatory")
    private String name;

    private String specialization;

    @NotBlank(message = "Contact number is mandatory")
    private String contactNumber;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender is mandatory")
    private String gender;

    private Integer createdAt;
}

