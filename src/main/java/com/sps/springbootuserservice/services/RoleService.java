package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.UserRoleDto;
import com.sps.springbootuserservice.model.Role;

import java.util.List;

public interface RoleService {
    UserRoleDto createRole(String roleName);

    List<UserRoleDto> getAllRoles();
}
