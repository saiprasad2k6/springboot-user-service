package com.sps.springbootuserservice.security;//package com.sps.springbootuserservice.security;

import com.sps.springbootuserservice.model.User;
import com.sps.springbootuserservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomSpringUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(username));

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return new CustomUserDetail(userOptional.get());
    }
}
