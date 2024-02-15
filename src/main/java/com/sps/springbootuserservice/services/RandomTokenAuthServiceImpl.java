package com.sps.springbootuserservice.services;

import com.sps.springbootuserservice.model.Session;
import com.sps.springbootuserservice.model.SessionStatus;
import com.sps.springbootuserservice.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Qualifier("randomTokenAuthService")
public class RandomTokenAuthServiceImpl extends AbstractAuthServiceImpl {

    @Override
    Session createUserSession(User user) {
        //Save the Session
        Session session = new Session();
        session.setUser(user);
        session.setToken(RandomStringUtils.randomAlphanumeric(30));
        session.setStatus(SessionStatus.ACTIVE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        session.setExpiringAt(calendar.getTime());
        return session;
    }

    @Override
    boolean validateToken(String token) {
        return token.length() > 0;
    }


}
