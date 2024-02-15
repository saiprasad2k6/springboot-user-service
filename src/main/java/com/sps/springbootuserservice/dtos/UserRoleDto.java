package com.sps.springbootuserservice.dtos;


import com.sps.springbootuserservice.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDto {
    private Long id;
    private String roleName;

    public static UserRoleDto from(Role role) {
        UserRoleDto roleDto = new UserRoleDto();
        roleDto.setRoleName(role.getRole());
        roleDto.setId(role.getId());
        return roleDto;
    }
}
