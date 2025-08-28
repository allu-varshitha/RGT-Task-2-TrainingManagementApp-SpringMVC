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



	String assignTrainingsToIndividualUser(Long trainingId, UserEntity userId);


	String assignTrainingToMultipleUsers(Long trainingId, List<UserEntity> users);

	List<TrainingEntity> getTrainingsForUser(Long userId);

	void markTrainingAsCompleted(Long trainingId, UserEntity userId);

	List<String> getOverdueTrainings(List<TrainingEntity> trainings);





}
