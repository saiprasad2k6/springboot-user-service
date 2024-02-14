package com.sps.springbootuserservice.dtos;

import com.sps.springbootuserservice.model.Role;
import com.sps.springbootuserservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String email;
    private List<UserRoleDto> roles;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());

        List<UserRoleDto> roleDtos = new ArrayList<>();
        for (Role role : user.getRoles()) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setRoleName(role.getRole());
            roleDtos.add(userRoleDto);
        }

        userDto.setRoles(roleDtos);
        return userDto;
    }
}
