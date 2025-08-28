package com.rgt.employee.service;

import com.rgt.employee.dto.*;
import com.rgt.employee.enums.Status;
import com.rgt.employee.model.TrainingEntity;
import com.rgt.employee.model.UserEntity;
import com.rgt.employee.repository.TrainingRepository;
import com.rgt.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

@Service
public class TrainingManagementServiceImpl implements TrainingManagementService {

    @Autowired
    private UserRepository urepo;

    @Autowired
    private TrainingRepository trepo;

    @Override
    public void addUsers(UserRequest request) {
        UserEntity user = new UserEntity();
        user.setUserName(request.getUserName());
        user.setRole(request.getRole());
        urepo.save(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = urepo.findAll();
        List<UserResponse> response = new ArrayList<>();
        for (UserEntity ue : users) {
            UserResponse ur = new UserResponse();
            ur.setUserId(ue.getUserId());
            ur.setUserName(ue.getUserName());
            ur.setRole(ue.getRole());
            response.add(ur);
        }
        return response;
    }

    @Override
    public void addTrainings(TrainingRequest request) {
        TrainingEntity training = new TrainingEntity();
        training.setTrainingTitle(request.getTrainingTitle());
        training.setTrainingDueDate(request.getTrainingDueDate());
        training.setMapUserWithStatus(new HashMap<>());
        trepo.save(training);
    }

    @Override
    public List<TrainingResponse> getAllTrainings() {
        List<TrainingEntity> trainings = trepo.findAll();
        List<TrainingResponse> response = new ArrayList<>();
        for (TrainingEntity te : trainings) {
            TrainingResponse tr = new TrainingResponse();
            tr.setTrainingId(te.getTrainingId());
            tr.setTrainingTitle(te.getTrainingTitle());
            tr.setTrainingdueDate(te.getTrainingDueDate());
            tr.setMapUserWithStatus(te.getMapUserWithStatus());
            response.add(tr);
        }
        return response;
    }
    @Override
    public String assignTrainingsToIndividualUser(Long trainingId, UserEntity userId) {
        TrainingEntity te = trepo.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        if (te.getMapUserWithStatus().containsKey(userId)) {
            return "User with id " + userId + " already exists in training";
        }

        te.getMapUserWithStatus().put(userId, Status.PENDING);
        trepo.save(te);
        return "Training assigned to user " + userId;
    }

    @Override
    public List<TrainingEntity> getTrainingsForUser(Long userId) {
        UserEntity user = urepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<TrainingEntity> trainings = trepo.findTrainingsByUser(user);
        if (trainings.isEmpty()) {
            throw new RuntimeException("No trainings assigned to this user");
        }
        return trainings;
    }


    @Override
    public String assignTrainingToMultipleUsers(Long trainingId, List<UserEntity> users) {
        TrainingEntity te = trepo.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        for (UserEntity user : users) {
            if (te.getMapUserWithStatus().containsKey(user)) {
                return "User with id " + user.getUserId() + " already exists in training";
            } else {
                te.getMapUserWithStatus().put(user, Status.PENDING);
            }
        }

        trepo.save(te);
        return "Training assigned to users";
    }


    @Override
    public void markTrainingAsCompleted(Long trainingId, UserEntity userId) {
        TrainingEntity te = trepo.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));
        if (te.getMapUserWithStatus().containsKey(userId)) {
            te.getMapUserWithStatus().put(userId, Status.COMPLETED);
           trepo.save(te);
        }
    }

    @Override
    public List<String> getOverdueTrainings(List<TrainingEntity> trainings) {
        List<String> response = new ArrayList<>();
        LocalDate today = LocalDate.now();//todays date 
        for (TrainingEntity te : trainings) {
            for (Entry<UserEntity, Status> entry : te.getMapUserWithStatus().entrySet()) {
            //	entrySet() returns a set of Map.Entry objects.
            //	Each Map.Entry represents one key-value pair inside the map.
                if (entry.getValue() == Status.PENDING && te.getTrainingDueDate().isBefore(today)) {
                	//getKey() -> returns the key.
                    //getValue() -> returns the value.
                    response.add("User ID: " + entry.getKey()
                            + ", Training: " + te.getTrainingTitle()
                            + ", Due Date: " + te.getTrainingDueDate());
                }
            }
        }
        return response;
    }

	
}
