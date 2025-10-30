package com.hiranwj.salonsync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "New password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Role is mandatory")
    private String role; // Example: ADMIN, STAFF, USER

    @NotBlank(message = "Gender is mandatory")
    private String gender; // Example: Male, Female, Other

    private String contactNumber;

    private Integer createdAt;
}
