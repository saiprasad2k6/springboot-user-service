package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.LoginRequestDto;
import com.sps.springbootuserservice.dtos.LogoutRequestDto;
import com.sps.springbootuserservice.dtos.UserDto;
import com.sps.springbootuserservice.dtos.SignupRequestDto;

public interface AuthService {
    UserDto login(LoginRequestDto loginRequestDto) throws Exception;

    void logout(LogoutRequestDto logoutRequestDto);

    UserDto signup(SignupRequestDto signupRequestDto) throws Exception;
}
