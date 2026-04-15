package com.juan.poveda.subjects.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SubjectDTO(

    Long id,

    @NotBlank(message = "Subject name is required")
    String name,

    @NotBlank(message = "Subject code is required")
    @Size(min = 3, max = 10, message = "Code must be between 3 and 10 characters")
    String code,

    @Positive(message = "Credits must be a positive number")
    Integer credits

) {}