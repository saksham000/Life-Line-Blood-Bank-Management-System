package com.lifeline.lifelinebloodbank.LifeLineProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifeline.lifelinebloodbank.LifeLineProject.dto.UserDto;
import com.lifeline.lifelinebloodbank.LifeLineProject.service.UserService;

@RestController
@RequestMapping(path = "/public")
public class PublicController {
    
    @Autowired
    private UserService userService;

    @GetMapping(path = "/test")
    public String testApi(){
        return "Public Api !";
    }

    @PostMapping(path = "/newuser")
    public UserDto newUser(@RequestBody UserDto dtoUser){
        return userService.registerNewUser(dtoUser);
    }

    
}
