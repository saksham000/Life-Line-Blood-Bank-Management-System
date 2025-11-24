package com.lifeline.lifelinebloodbank.LifeLineProject.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifeline.lifelinebloodbank.LifeLineProject.exception.ErrorDetails; // Import your error class
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // 1. Set the Response Status to 401 (Unauthorized)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 2. Create your custom ErrorDetails object
        // We reuse the class you created earlier for consistency
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Authentication Failed",
                authException.getMessage() // This will say "User not found" or "Bad credentials"
        );

        // 3. Convert the Java Object to JSON text manually using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        // This is necessary because LocalDateTime sometimes needs a module to print correctly
        objectMapper.findAndRegisterModules(); 
        
        objectMapper.writeValue(response.getOutputStream(), errorDetails);
    }
}