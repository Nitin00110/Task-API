package com.example.demo.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TasksRecordDTO(
        @NotBlank(message = "title is required")
        String title,

        String description,

        @Min(value = 1, message = "Urgency must be at least 1")
        @Max(value = 10, message = "Urgency must be at most 10")
        int urgent,

        @Min(value = 1, message = "Importance must be at least 1")
        @Max(value = 10, message = "Importance must be at most 10")
        int importance
) {
}