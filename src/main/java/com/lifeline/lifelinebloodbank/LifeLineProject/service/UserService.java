package com.lifeline.lifelinebloodbank.LifeLineProject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lifeline.lifelinebloodbank.LifeLineProject.dto.UserDto;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.Role;
import com.lifeline.lifelinebloodbank.LifeLineProject.entity.UserEnt;
import com.lifeline.lifelinebloodbank.LifeLineProject.repository.UserRepo;
import com.lifeline.lifelinebloodbank.LifeLineProject.security.JwtUtils;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    // @Autowired
    // private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserEnt> getAllUsers() {
        return userRepo.findAll();
    }

    // public String userLogin(UserDto dto){
    // Optional<UserEnt> optionalUser = userRepo.findByUserName(dto.getUserName());

    // if(optionalUser.isPresent()){
    // authenticationManager.authenticate(new
    // UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
    // }
    // UserDetails userDetails =
    // userDetailsServiceImpl.loadUserByUsername(dto.getUserName());
    // String jwt = jwtUtils.generateToken(userDetails);
    // return jwt;
    // }

    public String userLogin(UserDto dto) {
        try {
            // 1. Attempt to authenticate (Checks User, Admin, and Hospital automatically)
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));

            // 2. If successful, extract UserDetails directly from the result (No second DB
            // call needed)
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. Generate and return Token
            return jwtUtils.generateToken(userDetails);

        } catch (BadCredentialsException e) {
            // 4. Handle Wrong Password
            throw new RuntimeException("Invalid Username or Password");

        } catch (Exception e) {
            // 5. Handle User Not Found or other errors
            throw new RuntimeException("User not found or Authentication failed");
        }
    }

    public UserDto registerNewUser(UserDto dtoUser) {
        UserEnt newUser = UserEnt.builder()
                .userName(dtoUser.getUserName())
                .fullName(dtoUser.getFullName())
                .email(dtoUser.getEmail())
                .password(passwordEncoder.encode(dtoUser.getPassword()))
                .roles(Set.of(Role.USER))
                .build();
        userRepo.save(newUser);
        return dtoUser;
    }

    public void deleteUser(Long id) {
        Optional<UserEnt> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("User Id Not Found !");
        }
    }
}
