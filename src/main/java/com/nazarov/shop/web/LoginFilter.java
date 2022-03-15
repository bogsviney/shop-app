package com.nazarov.shop.web;

import com.nazarov.shop.service.security.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter implements Filter {
    private final SecurityService securityService;

    public LoginFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean isAuth = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (securityService.checkUserToken(cookie.getValue())) {
                        isAuth = true;
                    }
                    break;
                }
            }
        }
        if (isAuth) {
            filterChain.doFilter(request, response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
