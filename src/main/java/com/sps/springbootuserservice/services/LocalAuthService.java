package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.*;
import com.sps.springbootuserservice.exceptions.UserAlreadyExistsException;
import com.sps.springbootuserservice.model.Role;
import com.sps.springbootuserservice.model.User;
import com.sps.springbootuserservice.repositories.RoleRepository;
import com.sps.springbootuserservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class LocalAuthService implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto login(LoginRequestDto loginRequestDto) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequestDto.getEmail());
        if (optionalUser.isEmpty())
            throw new Exception("User Not Found");
        User response = optionalUser.get();

        if (!bCryptPasswordEncoder.matches(response.getPassword(), loginRequestDto.getPassword()))
            throw new RuntimeException("Wrong Username password");
        return UserDto.from(response);
    }

    @Override
    public void logout(LogoutRequestDto logoutRequestDto) {

    }

    @Override
    public UserDto signup(SignupRequestDto signupRequestDto) throws UserAlreadyExistsException {
        User user = new User();
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequestDto.getPassword()));

        Optional<User> userOptional = userRepository.findByEmail(signupRequestDto.getEmail());
        if (userOptional.isPresent()) throw new UserAlreadyExistsException(user.getEmail()+" already exists");

        Role role = roleRepository.findByRole("USER");
        user.getRoles().add(role);
        userRepository.save(user);
        return UserDto.from(user);
    }
}
