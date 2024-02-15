package com.sps.springbootuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDto {
    private String email;
    private String token;

}
