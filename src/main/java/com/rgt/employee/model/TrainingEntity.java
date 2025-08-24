package com.rgt.employee.model;

import com.rgt.employee.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "trainings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingId;
    private String trainingTitle;
    private LocalDate trainingDueDate;


    @ElementCollection
    @CollectionTable(name = "training_user_status", joinColumns = @JoinColumn(name = "training_id"))
    @MapKeyColumn(name = "user_id")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Map<Long, Status> mapUserWithStatus = new HashMap<>();

    public void assignTrainingToUsers(List<Long> userIds) {
        for (Long uid : userIds) {
            mapUserWithStatus.put(uid, Status.PENDING);
        }
    }
}
