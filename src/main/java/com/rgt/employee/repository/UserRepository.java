package com.rgt.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgt.employee.model.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long>{

}
