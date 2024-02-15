package com.sps.springbootuserservice.dtos;

import com.sps.springbootuserservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private UserDto userDto;
    private String token;
}
