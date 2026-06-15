package com.pharma.auth_service.Service;

import com.pharma.auth_service.Controller.AuthController;
import com.pharma.auth_service.DTO.Request.LoginRequest;
import com.pharma.auth_service.DTO.Request.RegisterRequest;
import com.pharma.auth_service.Entity.Role;
import com.pharma.auth_service.Entity.User;
import com.pharma.auth_service.JWT.Service.JwtService;
import com.pharma.auth_service.Repository.RoleRepository;
import com.pharma.auth_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleService roleService;
    private static final Logger log =
            LoggerFactory.getLogger(AuthService.class);


    // Register User Service
   public String register(RegisterRequest request) throws RoleNotFoundException {

       Role role = roleService.findById(request.roleId());

       User u = new User();
       u.setName(request.name());
       u.setEmail(request.email());
       u.setPassword(passwordEncoder.encode(request.password()));
       u.setRole(role);
       userRepository.save(u);
       log.info("User Registered Successfully");
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
