package com.nazarov.shop.service.security;

import com.nazarov.shop.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

public class SecurityService {

    private UserService userService;
    private List<Session> sessions = new ArrayList<>();
    Session session;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public void generateAndAddUserTokenToSession(Session session) {
        String token = UUID.randomUUID().toString();
        session.token = token;
        session.expireDate = LocalDateTime.now();
    }

    public String login(String email, String password) {

        if (userService.checkUser(email, password)) {
            session = new Session();
            generateAndAddUserTokenToSession(session);
            sessions.add(session);
            System.out.println("LOGGED IN! WELCOME!");
            return session.token;
        } else {
            System.out.println("LOGIN FAILED, TRY AGAIN!");
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        return sessions.stream()
                .filter(session -> session.token.equals(token))
                .anyMatch(session -> session.expireDate.isAfter(LocalDateTime.now()));
    }

}

