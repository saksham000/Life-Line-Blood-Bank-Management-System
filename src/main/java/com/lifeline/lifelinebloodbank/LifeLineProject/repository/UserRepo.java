package com.lifeline.lifelinebloodbank.LifeLineProject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Added import
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.UserEnt;

@Repository
public interface UserRepo extends JpaRepository<UserEnt, Long> {
    // Works perfectly because the field in UserEnt is named 'userName'
    Optional<UserEnt> findByUserName(String userName);
}