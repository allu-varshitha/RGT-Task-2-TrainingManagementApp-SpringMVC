package com.rgt.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rgt.employee.model.TrainingEntity;
import com.rgt.employee.model.UserEntity;

@Repository
public interface TrainingRepository  extends JpaRepository<TrainingEntity, Long>{

	@Query("SELECT t FROM TrainingEntity t JOIN t.mapUserWithStatus mus " +
		       "WHERE KEY(mus) = :user")
		List<TrainingEntity> findTrainingsByUser(@Param("user") UserEntity user);

}
