package com.lifeline.lifelinebloodbank.LifeLineProject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hospital")
public class HospitalController {

    @GetMapping(path = "/test")
    public String testApi(){
        return "Hospital Api";
    }

}
