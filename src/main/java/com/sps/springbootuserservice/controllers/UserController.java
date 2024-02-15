package com.sps.springbootuserservice.controllers;

import com.sps.springbootuserservice.dtos.UserDto;
import com.sps.springbootuserservice.repositories.UserRepository;
import com.sps.springbootuserservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("{email}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("email") String email) {
        UserDto userDto = userService.getUserDetails(email);
        return ResponseEntity.ok(userDto);
    }
}
