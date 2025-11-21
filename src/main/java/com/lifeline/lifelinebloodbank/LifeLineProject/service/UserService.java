package com.lifeline.lifelinebloodbank.LifeLineProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeline.lifelinebloodbank.LifeLineProject.dto.UserDto;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.UserEnt;
import com.lifeline.lifelinebloodbank.LifeLineProject.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    
    public List<UserEnt> getAllUsers(){
        return userRepo.findAll();
    }

    public UserDto registerNewUser(UserDto dtoUser){
        UserEnt newUser = UserEnt.builder()
        .userName(dtoUser.getUsername())
        .fullName(dtoUser.getFullName())
        .email(dtoUser.getEmail())
        .password(dtoUser.getPassword())
        .build();
        userRepo.save(newUser);
        return dtoUser;
    }
}
