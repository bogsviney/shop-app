package com.nazarov.shop.service.security;

import jakarta.servlet.http.*;

import java.util.*;

public class SecurityService {

    private List<String> userTokens = new ArrayList<>();
    private String userToken;

    public String generateUserToken() {
        userToken = UUID.randomUUID().toString();
        return userToken;
    }

    public void addUserToken(String userToken) {
        userTokens.add(userToken);
    }

    public void generateAndAddUserTokenToUserTokensList() {
        addUserToken(generateUserToken());
    }

    public List<String> getUserTokens() {
        return userTokens;
    }

    public String getUserToken(){
        return userToken;
    }

    public boolean checkUserToken(HttpServletRequest request, List<String> userTokens) {
        boolean isAuth = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (userTokens.contains(cookie.getValue())) {
                        isAuth = true;
                    }
                    break;
                }
            }
        }
        return isAuth;
    }
}
