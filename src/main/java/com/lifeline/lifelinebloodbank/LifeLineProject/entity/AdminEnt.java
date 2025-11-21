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
public class AdminEnt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fix 1: Auto-generate ID
    private Long adminId;

    private String userName; // This matches findByUserName

    private String email;

    private String fullName;

    private String password; // Fix 2: Corrected spelling

    private String superSecret; // Fix 3: Corrected spelling

    // Fix 4: Added mapping for the Set of Roles
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "admin_roles", joinColumns = @JoinColumn(name = "admin_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}