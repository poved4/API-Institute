package com.juan.poveda.grades.models.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record GradeDTO(

    Long id,

    @NotNull(message = "Grade value is required")
    @Min(0) @Max(5)
    Double value,

    LocalDateTime registrationDate,

    @NotNull(message = "Student ID is required")
    Long studentId,

    @NotNull(message = "Subject ID is required")
    Long subjectId

) {}