package com.lifeline.lifelinebloodbank.LifeLineProject.security;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lifeline.lifelinebloodbank.LifeLineProject.entity.AdminEnt;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.HospitalEnt;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.UserEnt;
import com.lifeline.lifelinebloodbank.LifeLineProject.repository.AdminRepo;
import com.lifeline.lifelinebloodbank.LifeLineProject.repository.HospitalRepo;
import com.lifeline.lifelinebloodbank.LifeLineProject.repository.UserRepo;

// import com.lms.lmsproject.LmsProject.repository.AdminRepo;
// import com.lms.lmsproject.LmsProject.repository.TeacherRepo;
// import com.lms.lmsproject.LmsProject.repository.UserEntRepo;
// import com.lms.lmsproject.LmsProject.entity.Admin;
// import com.lms.lmsproject.LmsProject.entity.Teacher;
// import com.lms.lmsproject.LmsProject.entity.UserEnt;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AdminEnt> adminOpt = adminRepo.findByUserName(username);
        if (adminOpt.isPresent()) {
            return buildUserDetails(adminOpt.get());
        }
    
        Optional<HospitalEnt> hospitalOpt = hospitalRepo.findByUserName(username);
        if (hospitalOpt.isPresent()) {
            return buildUserDetails(hospitalOpt.get());
        }
    
        Optional<UserEnt> userOpt = userRepo.findByUserName(username);
        if (userOpt.isPresent()) {
            return buildUserDetails(userOpt.get());
        }

        // If neither found, throw exception
        throw new UsernameNotFoundException("User not found");
    }

    private UserDetails buildUserDetails(UserEnt user) {
        String[] roles = user.getRoles().stream()
                .map(role -> role.name())
                .toArray(String[]::new);

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    private UserDetails buildUserDetails(HospitalEnt hospital) {
        String[] roles = hospital.getRoles().stream()
                .map(role -> role.name())
                .toArray(String[]::new);

        return User.builder()
                .username(hospital.getUserName())
                .password(hospital.getHosPassword())
                .roles(roles)
                .build();
    }

    private UserDetails buildUserDetails(AdminEnt admin) {
        String[] roles = admin.getRoles().stream()
                .map(role -> role.name())
                .toArray(String[]::new);

        System.out
                .println("Admin roles: !!!!!!!!!!!!!!!!!!!!_________________----------------" + Arrays.toString(roles));
        return User.builder()
                .username(admin.getUserName())
                .password(admin.getPassword())
                .roles(roles)
                .build();
    }
}
