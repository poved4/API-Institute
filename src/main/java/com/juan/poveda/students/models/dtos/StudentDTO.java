package com.juan.poveda.students.models.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record StudentDTO(

    Long id,

    @NotBlank(message = "First name is required")
    String firstName,

    @NotBlank(message = "Last name is required")
    String lastName,

    @Email(message = "Email should be valid")
    String email,

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Birth date must be in the past")
    LocalDate birthDate

) {}