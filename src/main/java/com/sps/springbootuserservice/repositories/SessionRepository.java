package com.sps.springbootuserservice.repositories;

import com.sps.springbootuserservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findSessionByToken(String token);
}
