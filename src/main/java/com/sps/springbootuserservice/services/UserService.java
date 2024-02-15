package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserDetails(String email);
    UserDto setUserRoles(String email, List<Long> roleIds);

}
