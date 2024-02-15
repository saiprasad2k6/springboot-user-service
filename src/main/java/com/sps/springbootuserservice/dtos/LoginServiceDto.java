package com.sps.springbootuserservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginServiceDto {
    private UserDto userDto;
    private String token;
}
