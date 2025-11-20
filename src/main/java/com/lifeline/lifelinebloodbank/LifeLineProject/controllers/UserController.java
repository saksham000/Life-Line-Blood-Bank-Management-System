package com.lifeline.lifelinebloodbank.LifeLineProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeline.lifelinebloodbank.LifeLineProject.dto.UserDto;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.User;
import com.lifeline.lifelinebloodbank.LifeLineProject.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    // @Autowired
    // private UserDto userDto;

    @Autowired
    private UserService  userService;

    @GetMapping(path = "/all-users")
    public List<User> allUsers(UserDto userDto){
        return userService.getAllUsers();
    }

}
