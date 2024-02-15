package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.model.Session;
import com.sps.springbootuserservice.model.SessionStatus;
import com.sps.springbootuserservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
@Qualifier("jwtTokenAuthService")
@Primary
public class JwtTokenAuthServiceImpl extends AbstractAuthServiceImpl {

    private SecretKey secretKey;

    @Override
    protected Session createUserSession(User user) {
        Session session = new Session();
        session.setUser(user);
        session.setToken(createJWTForUser(user));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        session.setExpiringAt(calendar.getTime());
        session.setStatus(SessionStatus.ACTIVE);
        return session;
    }

    @Override
    boolean validateToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token);

        Date expiryAt = new Date((Long) claimsJws.getPayload().get("expiryAt"));

        return !expiryAt.before(new Date());
    }

    private String createJWTForUser(User user) {
        if (secretKey == null) {
            secretKey = Jwts.SIG.HS256.key().build();
        }

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("createAt", new Date());
        jsonForJwt.put("expiryAt", findDateForExpiry());

        return Jwts.builder().claims(jsonForJwt).signWith(secretKey).compact();
    }

    private Date findDateForExpiry() {
        Calendar expiryAtCalendar = Calendar.getInstance();
        expiryAtCalendar.add(Calendar.WEEK_OF_MONTH, 1);
        return expiryAtCalendar.getTime();
    }
}
