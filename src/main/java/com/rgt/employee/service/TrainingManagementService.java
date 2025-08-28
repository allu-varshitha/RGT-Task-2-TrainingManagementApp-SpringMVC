package com.rgt.employee.service;

import java.util.List;

import com.rgt.employee.dto.TrainingRequest;
import com.rgt.employee.dto.TrainingResponse;
import com.rgt.employee.dto.UserRequest;
import com.rgt.employee.dto.UserResponse;
import com.rgt.employee.model.TrainingEntity;
import com.rgt.employee.model.UserEntity;

public interface TrainingManagementService {

    void addUsers(UserRequest request);

    List<UserResponse> getAllUsers();

    void addTrainings(TrainingRequest request);

    List<TrainingResponse> getAllTrainings();

    List<TrainingEntity> getTrainingsForUser(Long userId);

    List<String> getOverdueTrainings(List<TrainingEntity> trainings);

<<<<<<< HEAD
    List<String> getOverdueTrainings(List<TrainingEntity> trainings);
=======
	String assignTrainingsToIndividualUser(Long trainingId, UserEntity userId);

	void markTrainingAsCompleted(Long trainingId, UserEntity userId);

	String assignTrainingToMultipleUsers(Long trainingId, List<UserEntity> users);
>>>>>>> 89aa132 (feat(RGT) TMA optimized code)
}
