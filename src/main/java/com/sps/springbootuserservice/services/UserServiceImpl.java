package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.UserDto;
import com.sps.springbootuserservice.dtos.UserRoleDto;
import com.sps.springbootuserservice.model.Role;
import com.sps.springbootuserservice.repositories.RoleRepository;
import com.sps.springbootuserservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDto getUserDetails(String email) {
        return UserDto.from(userRepository.findByEmail(email));
    }

    @Override
    public UserDto setUserRoles(String email, List<Long> roleId) {
        UserDto userDto = UserDto.from(userRepository.findByEmail(email));

        List<Role> roleList = roleRepository.findAllByIdIn(roleId);
        roleList.forEach((role) -> userDto.getRoles().add(UserRoleDto.from(role)));
        return userDto;
    }

}
