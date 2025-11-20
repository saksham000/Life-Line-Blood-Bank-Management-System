package com.lifeline.lifelinebloodbank.LifeLineProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifeline.lifelinebloodbank.LifeLineProject.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    
}
