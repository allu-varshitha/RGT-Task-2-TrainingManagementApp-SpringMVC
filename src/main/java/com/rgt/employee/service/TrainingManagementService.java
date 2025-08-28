package com.rgt.employee.service;

import java.util.List;

import com.rgt.employee.dto.TrainingRequest;
import com.rgt.employee.dto.TrainingResponse;
import com.rgt.employee.dto.UserRequest;
import com.rgt.employee.dto.UserResponse;
import com.rgt.employee.model.TrainingEntity;

public interface TrainingManagementService {

    void addUsers(UserRequest request);

    List<UserResponse> getAllUsers();

    void addTrainings(TrainingRequest request);

    List<TrainingResponse> getAllTrainings();

    void assignTrainingsToIndividualUser(Long trainingId, Long userId);

    void assignTrainingToMultipleUsers(Long trainingId, List<Long> userIds);

    List<TrainingEntity> getTrainingsForUser(Long userId);

    void markTrainingAsCompleted(Long trainingId, Long userId);

    List<String> getOverdueTrainings(List<TrainingEntity> trainings);
}
