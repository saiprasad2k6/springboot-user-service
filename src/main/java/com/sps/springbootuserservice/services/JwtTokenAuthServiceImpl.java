package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.model.Session;
import com.sps.springbootuserservice.model.SessionStatus;
import com.sps.springbootuserservice.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
@Qualifier("jwtTokenAuthService")
@Primary
public class JwtTokenAuthServiceImpl extends AbstractAuthServiceImpl {

    @Override
    protected Session createUserSession(User user) {
        Session session = new Session();
        session.setUser(user);
        session.setToken(createJWTForUser(user));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        session.setExpiringAt(calendar.getTime());
        session.setStatus(SessionStatus.ACTIVE);
        return session;
    }

    private String createJWTForUser(User user) {
        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("createAt", new Date());
        jsonForJwt.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        return Jwts.builder().claims(jsonForJwt).signWith(key, alg).compact();
    }


}
