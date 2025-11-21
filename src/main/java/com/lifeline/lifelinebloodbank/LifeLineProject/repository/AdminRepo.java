package com.lifeline.lifelinebloodbank.LifeLineProject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.AdminEnt;

@Repository
public interface AdminRepo extends JpaRepository<AdminEnt, Long> {
    // This works perfectly because the field is 'userName'
    Optional<AdminEnt> findByUserName(String userName);
}