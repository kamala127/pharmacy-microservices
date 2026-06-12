package com.pharma.auth_service.Service;

import com.pharma.auth_service.DTO.AddRoleRequest;
import com.pharma.auth_service.Entity.Role;
import com.pharma.auth_service.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public String addRole(AddRoleRequest request){

        Role role = new Role();
        role.setRoleName(request.roleName());
        roleRepository.save(role);
        return "Role Added Successfully";
    }

    // getAll
    public List<Role> getAllRole(){
       return roleRepository.findAll();
    }

    // Update Role
    public String updateRole(Long id, AddRoleRequest request) throws RoleNotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(()-> new RoleNotFoundException("Id Not Found"));
        role.setRoleName(request.roleName());
        roleRepository.save(role);
        return "Role Name Updated";
    }

}
