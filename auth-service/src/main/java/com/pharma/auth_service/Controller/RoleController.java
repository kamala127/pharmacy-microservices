package com.pharma.auth_service.Controller;

import com.pharma.auth_service.DTO.AddRoleRequest;
import com.pharma.auth_service.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public String addRole(@RequestBody AddRoleRequest request){

        return roleService.addRole(request);
    }

}
