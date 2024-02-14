package com.sps.springbootuserservice.controllers;

import com.sps.springbootuserservice.dtos.LoginRequestDto;
import com.sps.springbootuserservice.dtos.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.sps.springbootuserservice.services.AuthService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    @Test
    void returnsUserForProperLoginRequest() throws Exception {
        UserDto userDtoMockResponse = new UserDto();
        userDtoMockResponse.setEmail("abc@gmail.com");
        userDtoMockResponse.setRoles(new ArrayList<>());
        when(authService.login(any(LoginRequestDto.class))).thenReturn(userDtoMockResponse);

        LoginRequestDto loginRequestDtoMock = new LoginRequestDto();
        loginRequestDtoMock.setEmail("abc@gmail.com");
        loginRequestDtoMock.setPassword("somepassword");
        UserDto userDtoResponseEntity = authController.login(loginRequestDtoMock).getBody();

        Assertions.assertNotNull(userDtoResponseEntity);
        Assertions.assertNotNull(userDtoResponseEntity.getEmail());
        Assertions.assertNotNull(userDtoMockResponse.getRoles());
    }

}
