package com.pharma.auth_service.Service;

import com.pharma.auth_service.DTO.LoginRequest;
import com.pharma.auth_service.DTO.RegisterRequest;
import com.pharma.auth_service.Entity.Role;
import com.pharma.auth_service.Entity.User;
import com.pharma.auth_service.JWT.Service.JwtService;
import com.pharma.auth_service.Repository.RoleRepository;
import com.pharma.auth_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    // Register User Service
   public String register(RegisterRequest request){

       Role role = roleRepository.findById(request.roleId()).orElseThrow();

       User u = new User();
       u.setName(request.name());
       u.setEmail(request.email());
       u.setPassword(passwordEncoder.encode(request.password()));
       u.setRole(role);
       userRepository.save(u);
       return "User Registered Successfully";
   }

   // Login User Service
   public String login(LoginRequest request){

       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.username(),
                       request.password()
               )
       );

       return jwtService.generateToken(
               request.username()
       );
   }



}
