package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.*;

public interface AuthService {
    LoginServiceDto login(LoginRequestDto loginRequestDto) throws Exception;

    void logout(LogoutDto logoutServiceRequestDto) throws Exception;

    UserDto signup(SignupDto signupRequestDto) throws Exception;
}
