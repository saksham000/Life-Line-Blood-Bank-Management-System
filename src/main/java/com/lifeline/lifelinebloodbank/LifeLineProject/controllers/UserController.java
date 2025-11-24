package com.lifeline.lifelinebloodbank.LifeLineProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeline.lifelinebloodbank.LifeLineProject.dto.UserDto;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.UserEnt;
import com.lifeline.lifelinebloodbank.LifeLineProject.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService  userService;

    @GetMapping(path = "/test")
    public String testApi(){
        return "user API !";
    }

    @GetMapping(path = "/all-users")
    public List<UserEnt> allUsers(UserDto userDto){
        return userService.getAllUsers();
    }


}
