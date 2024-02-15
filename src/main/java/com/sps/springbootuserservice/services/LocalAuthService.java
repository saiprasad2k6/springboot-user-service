package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.*;
import com.sps.springbootuserservice.exceptions.UserAlreadyExistsException;
import com.sps.springbootuserservice.model.Role;
import com.sps.springbootuserservice.model.Session;
import com.sps.springbootuserservice.model.User;
import com.sps.springbootuserservice.repositories.RoleRepository;
import com.sps.springbootuserservice.repositories.SessionRepository;
import com.sps.springbootuserservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@Primary
public class LocalAuthService implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginServiceDto login(LoginRequestDto loginRequestDto) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequestDto.getEmail());
        if (optionalUser.isEmpty())
            throw new Exception("User Not Found");
        User user = optionalUser.get();

        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()))
            throw new RuntimeException("Wrong Username password");

        //Save the Session
        Session session = new Session();
        session.setUser(user);
        session.setToken(RandomStringUtils.randomAlphanumeric(30));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        session.setExpiringAt(calendar.getTime());
        sessionRepository.save(session);

        return new LoginServiceDto(UserDto.from(user), session.getToken());
    }

    @Override
    public void logout(LogoutDto logoutDto) throws Exception {
        //Find the token in Session
        Session session = sessionRepository.findSessionByToken(logoutDto.getToken());
        if (session == null) {
            throw new Exception("Session Not Found");
        }

        // Delete them
        sessionRepository.delete(session);
    }

    @Override
    public UserDto signup(SignupDto signupRequestDto) throws UserAlreadyExistsException {
        User user = new User();
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequestDto.getPassword()));

        Optional<User> userOptional = userRepository.findByEmail(signupRequestDto.getEmail());
        if (userOptional.isPresent()) throw new UserAlreadyExistsException(user.getEmail() + " already exists");

        Role role = roleRepository.findByRole("USER");
        user.getRoles().add(role);
        userRepository.save(user);
        return UserDto.from(user);
    }
}
