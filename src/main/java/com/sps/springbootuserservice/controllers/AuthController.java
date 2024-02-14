package com.sps.springbootuserservice.controllers;

import com.sps.springbootuserservice.dtos.LoginRequestDto;
import com.sps.springbootuserservice.dtos.SignupRequestDto;
import com.sps.springbootuserservice.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sps.springbootuserservice.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) throws Exception {
        UserDto userDto = authService.signup(signupRequestDto);
        if (userDto == null) throw new Exception("Unable to Register");
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        UserDto userDto = authService.login(loginRequestDto);
        if (userDto == null) throw new Exception("User Not Found");
        return ResponseEntity.ok(userDto);
    }

    public void logout(@RequestBody SignupRequestDto signupRequestDto) {

    }

}
