package com.pharma.auth_service.Controller;

import com.pharma.auth_service.DTO.Request.*;
import com.pharma.auth_service.JWT.Service.JwtService;
import com.pharma.auth_service.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // Register User
    @PostMapping("/register")
    public String register(
            @Valid
            @RequestBody RegisterRequest request) throws RoleNotFoundException {

        return authService.register(request);
    }

    // Login User
    @PostMapping("/login")
    public String login(

            @Valid
            @RequestBody LoginRequest request){

        return authService.login(request);
    }
}
