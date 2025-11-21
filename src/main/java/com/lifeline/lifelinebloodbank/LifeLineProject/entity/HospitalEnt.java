package com.lifeline.lifelinebloodbank.LifeLineProject.entity;

import java.util.Set;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalEnt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Added: Auto-generates ID
    private Long hosId;

    private String hospitalName;

    private String userName;

    private String hosPassword;

    // Added: JPA Mapping for Roles
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "hospital_roles", joinColumns = @JoinColumn(name = "hospital_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}