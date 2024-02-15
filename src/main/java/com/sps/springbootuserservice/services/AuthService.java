package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.*;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception;

    void logout(LogoutDto logoutServiceRequestDto) throws Exception;

    UserDto signup(SignupRequestDto signupRequestDto) throws Exception;
}
