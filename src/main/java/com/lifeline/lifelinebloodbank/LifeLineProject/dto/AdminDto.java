package com.lifeline.lifelinebloodbank.LifeLineProject.dto;

import lombok.Data;

@Data
public class AdminDto {
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private String superSecret;
}
