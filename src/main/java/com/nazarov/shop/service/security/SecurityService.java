package com.nazarov.shop.service.security;

import com.nazarov.shop.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

public class SecurityService {

    public static final int SESSION_LIFETIME_IN_MINUTES = 15;
    private UserService userService;
    private List<Session> sessions;

    public SecurityService(UserService userService) {
        this.userService = userService;
        this.sessions = new ArrayList<>();
    }

    public void generateAndAddUserTokenToSession(Session session) {
        String token = UUID.randomUUID().toString();
        session.setToken(token);
        session.setExpireDate(LocalDateTime.now().plusMinutes(SESSION_LIFETIME_IN_MINUTES));
    }

    public String login(String email, String password) {
        if (userService.checkUser(email, password)) {
            Session session = new Session();
            generateAndAddUserTokenToSession(session);
            sessions.add(session);
            System.out.println("SECURITY SERVICE: LOGGED IN! WELCOME!");
            return session.getToken();
        } else {
            System.out.println("SECURITY SERVICE: LOGIN FAILED, TRY AGAIN!");
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        return sessions.stream()
                .filter(session -> session.getToken().equals(token))
                .anyMatch(session -> session.getExpireDate().isAfter(LocalDateTime.now()));
    }

    public Session getSession(String token) {
        if (isTokenValid(token)) {
            System.out.println("SECURITY SERVICE: token is valid");
            return sessions.get(sessions.indexOf(token));
        } else {
            System.out.println("SECURITY SERVICE: token is not valid");
            return null;
        }
    }
}

