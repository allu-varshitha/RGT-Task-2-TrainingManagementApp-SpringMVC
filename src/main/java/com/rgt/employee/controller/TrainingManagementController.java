package com.rgt.employee.controller;

import com.rgt.employee.dto.*;
import com.rgt.employee.model.TrainingEntity;
import com.rgt.employee.service.TrainingManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingManagementController {

	@Autowired
    private TrainingManagementService service;
	
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRequest request) {
        service.addUsers(request);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<String> addTraining(@Valid @RequestBody TrainingRequest request) {
        service.addTrainings(request);
        return ResponseEntity.ok("Training added successfully");
    }

    @GetMapping
    public ResponseEntity<List<TrainingResponse>> getAllTrainings() {
        return ResponseEntity.ok(service.getAllTrainings());
    }

    @PostMapping("/{trainingId}/assign/user/{userId}")
    public ResponseEntity<String> assignTrainingToUser(@PathVariable Long trainingId, @PathVariable Long userId) {
        service.assignTrainingsToIndividualUser(trainingId, userId);
        return ResponseEntity.ok("Training assigned to user " + userId);
    }

    @PostMapping("/{trainingId}/assign/users")
    public ResponseEntity<String> assignTrainingToMultipleUsers(
            @PathVariable Long trainingId,
            @RequestBody List<Long> userIds) {
        service.assignTrainingToMultipleUsers(trainingId, userIds);
        return ResponseEntity.ok("Training assigned to multiple users");
    }

   
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<TrainingEntity>> getTrainingsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getTrainingsForUser(userId));
    }

    @PutMapping("/{trainingId}/complete/{userId}")
    public ResponseEntity<String> markTrainingCompleted(@PathVariable Long trainingId, @PathVariable Long userId) {
        service.markTrainingAsCompleted(trainingId, userId);
        return ResponseEntity.ok("Training marked as completed for user " + userId);
    }

    @GetMapping("/users/{userId}/overdue")
    public ResponseEntity<List<String>> getOverdueTrainings(@PathVariable Long userId) {
        List<TrainingEntity> trainings = service.getTrainingsForUser(userId);
        return ResponseEntity.ok(service.getOverdueTrainings(trainings));
    }
}
