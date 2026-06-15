package com.pharma.auth_service.Controller;

import com.pharma.auth_service.DTO.Request.AddRoleRequest;
import com.pharma.auth_service.Entity.Role;
import com.pharma.auth_service.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public String addRole(@RequestBody AddRoleRequest request){

        System.out.println(request.roleName());
        return roleService.addRole(request);
    }

    @GetMapping("/getAll")
    public List<Role> getAll(){
        return roleService.getAllRole();
    }

    @PutMapping("/updateRole/{id}")
    public String roleUpdate(@PathVariable Long id, @RequestBody AddRoleRequest request) throws RoleNotFoundException {
        return roleService.updateRole(id,request);
    }

}
