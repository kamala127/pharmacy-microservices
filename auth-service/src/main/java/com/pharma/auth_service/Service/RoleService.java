package com.pharma.auth_service.Service;

import com.pharma.auth_service.DTO.AddRoleRequest;
import com.pharma.auth_service.Entity.Role;
import com.pharma.auth_service.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public String addRole(AddRoleRequest request){

        Role role = new Role();
        role.setRoleName(request.rolename());
        roleRepository.save(role);
        return "Role Added Successfully";
    }

}
