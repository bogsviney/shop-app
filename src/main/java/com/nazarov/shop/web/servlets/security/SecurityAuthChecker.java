package com.nazarov.shop.web.servlets.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SecurityAuthChecker {
    public boolean checkUserAuthorization(HttpServletRequest request, List<String> userTokens) {
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
