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
    public void assignTrainingsToIndividualUser(Long trainingId, Long userId) {
        TrainingEntity te = trepo.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        if (te.getMapUserWithStatus().containsKey(userId)) {
            System.out.println("User with ID " + userId + " already exists in this training");
            return;
        }

        te.getMapUserWithStatus().put(userId, Status.PENDING);
        trepo.save(te);
        System.out.println("Training assigned to user " + userId);
    }

    @Override
    public List<TrainingEntity> getTrainingsForUser(Long userId) {
        List<TrainingEntity> trainings = trepo.findAll();
        List<TrainingEntity> res = new ArrayList<>();
        for (TrainingEntity te : trainings) {
            if (te.getMapUserWithStatus().containsKey(userId)) {
                res.add(te);
            }
        }
        return res;
    }

    @Override
    public void assignTrainingToMultipleUsers(Long trainingId, List<Long> userIds) {
        TrainingEntity te = trepo.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));
        te.assignTrainingToUsers(userIds);
        trepo.save(te);
    }

    @Override
    public void markTrainingAsCompleted(Long trainingId, Long userId) {
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
        LocalDate today = LocalDate.now();
        for (TrainingEntity te : trainings) {
            for (Map.Entry<Long, Status> entry : te.getMapUserWithStatus().entrySet()) {
                if (entry.getValue() == Status.PENDING && te.getTrainingDueDate().isBefore(today)) {
                    response.add("User ID: " + entry.getKey()
                            + ", Training: " + te.getTrainingTitle()
                            + ", Due Date: " + te.getTrainingDueDate());
                }
            }
        }
        return response;
    }
}
