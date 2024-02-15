package com.sps.springbootuserservice.controllers;

import com.sps.springbootuserservice.dtos.UserRoleDto;
import com.sps.springbootuserservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping()
    public UserRoleDto createRole(@RequestBody UserRoleDto userRoleDto) {
        return roleService.createRole(userRoleDto.getRoleName());
    }

    @GetMapping()
    public List<UserRoleDto> getRoles() {
        return roleService.getAllRoles();
    }
}
