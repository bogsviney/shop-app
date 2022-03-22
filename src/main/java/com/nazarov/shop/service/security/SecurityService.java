package com.nazarov.shop.service.security;

import com.nazarov.shop.service.UserService;
import jakarta.servlet.http.Cookie;

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
            session.setRole(Role.valueOf(userService.getUserRole(email)));
            System.out.println("SECURITY SERVICE: IN THIS SESSION ROLE SETTED TO: " + session.getRole().toString());
            System.out.println("SECURITY SERVICE: LOGGED IN! WELCOME!");
            return session.getToken();
        } else {
            System.out.println("SECURITY SERVICE: LOGIN FAILED, TRY AGAIN!");
            return null;
        }
    }

    public Role getUserRole(Session session) {
        if (session.getRole().equals(Role.USER)) {
            return Role.USER;
        } else if (session.getRole().equals(Role.ADMIN)) {
            return Role.ADMIN;
        } else {
            return Role.GUEST;
        }
    }

    public boolean isTokenValid(String token) {
        return sessions.stream()
                .filter(session -> session.getToken().equals(token))
                .anyMatch(session -> session.getExpireDate().isAfter(LocalDateTime.now()));
    }

    public Session getSession(String token) {
        Session sessionToReturn = sessions.stream()
                .filter(session -> token.equals(session.getToken()))
                .findFirst()
                .orElse(null);
        return sessionToReturn;
    }

    public String getTokenFromCookies(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("user-token"))
                .findFirst()
                .map(Cookie::getValue).orElse(null);
    }
}

