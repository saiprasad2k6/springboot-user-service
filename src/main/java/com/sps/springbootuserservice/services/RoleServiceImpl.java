package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.UserRoleDto;
import com.sps.springbootuserservice.model.Role;
import com.sps.springbootuserservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserRoleDto createRole(String roleName) {
        Role role = new Role();
        role.setRole(roleName);

        Role newRole = roleRepository.save(role);
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setRoleName(roleName);
        userRoleDto.setId(role.getId());
        return userRoleDto;
    }

    @Override
    public List<UserRoleDto> getAllRoles() {
        List<Role> rolesList = roleRepository.findAll();
        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        //rolesList.forEach((role -> userRoleDtos.add(UserRoleDto.from(role))));

        for (Role role : rolesList) {
            userRoleDtos.add(UserRoleDto.from(role));
        }
        return userRoleDtos;
    }
}
