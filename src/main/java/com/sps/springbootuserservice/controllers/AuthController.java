package com.sps.springbootuserservice.controllers;

import com.sps.springbootuserservice.dtos.*;
import com.sps.springbootuserservice.model.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import com.sps.springbootuserservice.services.AuthService;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupRequestDto) throws Exception {
        UserDto userDto = authService.signup(signupRequestDto);
        if (userDto == null) throw new Exception("Unable to Register");
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        LoginServiceDto responseDto = authService.login(loginRequestDto);
        if (responseDto == null) throw new Exception("User Not Found");

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + responseDto.getToken());
        return new ResponseEntity<>(responseDto.getUserDto(), headers, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutDto logoutDto, @CookieValue(name = "auth-token") String token) throws Exception {
        logoutDto.setToken(token);
        authService.logout(logoutDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenDto validateTokenDto) {
        SessionStatus sessionStatus = authService.validate(validateTokenDto.getToken(), validateTokenDto.getEmail());
        return ResponseEntity.ok(sessionStatus);
    }

}
