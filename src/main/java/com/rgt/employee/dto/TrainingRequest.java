package com.rgt.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainingRequest {
    @NotBlank(message = "Training title is required")
    private String trainingTitle;

    @NotNull(message = "Due date is required")
    private LocalDate trainingDueDate;
}
