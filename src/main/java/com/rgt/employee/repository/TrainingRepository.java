package com.rgt.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgt.employee.model.TrainingEntity;

@Repository
public interface TrainingRepository  extends JpaRepository<TrainingEntity, Long>{

}
