package com.rgt.employee.dto;

import com.rgt.employee.enums.Status;
import com.rgt.employee.model.UserEntity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class TrainingResponse {
    private Long trainingId;
    private String trainingTitle;
    private LocalDate trainingdueDate;
    private Map<UserEntity, Status> mapUserWithStatus;
}
