package com.pharma.auth_service.Controller;

import com.pharma.auth_service.DTO.LoginRequest;
import com.pharma.auth_service.DTO.RegisterRequest;
import com.pharma.auth_service.JWT.Service.JwtService;
import com.pharma.auth_service.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // Register User
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){

        return authService.register(request);
    }

    // Login User
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){

        return authService.login(request);
    }
}
