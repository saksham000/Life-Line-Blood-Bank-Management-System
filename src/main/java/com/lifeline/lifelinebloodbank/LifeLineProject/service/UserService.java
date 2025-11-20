package com.lifeline.lifelinebloodbank.LifeLineProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeline.lifelinebloodbank.LifeLineProject.dto.UserDto;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.User;
import com.lifeline.lifelinebloodbank.LifeLineProject.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User newUser(UserDto user){
        User newUser = User.builder()
        .username(user.getUsername())
        .fullName(user.getFullName())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
        return userRepo.save(newUser);
    }
}
