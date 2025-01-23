package com.sps.springbootuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignupDto {
    private String email;
    private String password;
    private List<String> roles;
}
