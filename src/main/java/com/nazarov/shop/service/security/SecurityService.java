package com.nazarov.shop.service.security;

import com.nazarov.shop.service.UserService;

import java.util.*;

public class SecurityService {

    private UserService userService;
    private List<String> userTokens = new ArrayList<>();
    private String userToken;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public String generateUserToken() {
        userToken = UUID.randomUUID().toString();
        return userToken;
    }

    public void addUserToken(String userToken) {
        userTokens.add(userToken);
    }

    public String getUserToken() {
        return userToken;
    }

    public boolean checkUserToken(String token) {
        return userTokens.contains(token);
    }

    public String login(String email, String password) {
        if (userService.checkUser(email, password)) {
            String token = generateUserToken();
            addUserToken(token);
            System.out.println("LOGGED IN! WELCOME!");
            return token;
        } else {
            System.out.println("LOGIN FAILED, TRY AGAIN!");
            return null;
        }
    }
}
