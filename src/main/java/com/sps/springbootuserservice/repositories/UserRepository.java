package com.sps.springbootuserservice.repositories;

import com.sps.springbootuserservice.dtos.UserDto;
import com.sps.springbootuserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
