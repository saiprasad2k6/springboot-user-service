package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.dtos.*;
import com.sps.springbootuserservice.exceptions.UserAlreadyExistsException;
import com.sps.springbootuserservice.model.Role;
import com.sps.springbootuserservice.model.Session;
import com.sps.springbootuserservice.model.SessionStatus;
import com.sps.springbootuserservice.model.User;
import com.sps.springbootuserservice.repositories.RoleRepository;
import com.sps.springbootuserservice.repositories.SessionRepository;
import com.sps.springbootuserservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Optional;

public abstract class AbstractAuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    protected SessionRepository sessionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    abstract Session createUserSession(User user);

    abstract boolean validateToken(String token);

    @Override
    public LoginServiceDto login(LoginRequestDto loginRequestDto) throws Exception {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(loginRequestDto.getEmail()));
        if (optionalUser.isEmpty())
            throw new Exception("User Not Found");
        User user = optionalUser.get();

        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()))
            throw new RuntimeException("Wrong Username password");

        Session session = createUserSession(user);
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

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(signupRequestDto.getEmail()));
        if (userOptional.isPresent()) throw new UserAlreadyExistsException(user.getEmail() + " already exists");

        for (String roleString : signupRequestDto.getRoles()) {
            Role role = roleRepository.findByRole(roleString.toUpperCase());
            if(role !=null)  user.getRoles().add(role);
        }

        userRepository.save(user);
        return UserDto.from(user);
    }

    @Override
    public SessionStatus validate(String token, String email) {
        Session session = sessionRepository.findSessionByTokenAndUser_Email(token, email);
        if (session.getStatus().equals(SessionStatus.ENDED)) return SessionStatus.ENDED;
        if (session.getExpiringAt().before(new Date())) {
            return SessionStatus.ENDED;
        }

        if (!validateToken(token)) return SessionStatus.ENDED;

        return SessionStatus.ACTIVE;
    }


}
