package com.lifeline.lifelinebloodbank.LifeLineProject.entity;

import java.util.Set;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Good practice to rename table to avoid SQL keyword conflicts (USER is reserved in some DBs)
@Table(name = "app_users") 
public class UserEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String fullName;

    private String email;

    private String password;

    // FIXED: Added JPA mapping for the Set of Roles
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}