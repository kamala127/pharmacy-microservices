package com.pharma.auth_service.Service;

import com.pharma.auth_service.DTO.RegisterRequest;
import com.pharma.auth_service.Entity.Role;
import com.pharma.auth_service.Entity.User;
import com.pharma.auth_service.Repository.RoleRepository;
import com.pharma.auth_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

   public String register(RegisterRequest request){

       Role role = roleRepository.findById(request.roleId()).orElseThrow();

       User u = new User();
       u.setName(request.name());
       u.setEmail(request.email());
       u.setPassword(request.password());
       u.setRole(role);
       userRepository.save(u);
       return "User Registered Successfully";
   }


}
