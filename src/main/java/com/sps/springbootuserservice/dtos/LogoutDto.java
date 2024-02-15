package com.sps.springbootuserservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogoutDto {
    private String email;
    @JsonIgnore
    private String token;
}
