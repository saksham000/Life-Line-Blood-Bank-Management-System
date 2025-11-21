package com.lifeline.lifelinebloodbank.LifeLineProject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Added import
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.HospitalEnt;

@Repository // Added annotation
public interface HospitalRepo extends JpaRepository<HospitalEnt, Long> {

    // This is correct because 'userName' matches the field in HospitalEnt
    Optional<HospitalEnt> findByUserName(String userName);
}