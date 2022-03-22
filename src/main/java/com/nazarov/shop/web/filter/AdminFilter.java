package com.nazarov.shop.web.filter;

import com.nazarov.shop.service.security.Role;
import com.nazarov.shop.service.security.SecurityService;
import com.nazarov.shop.service.security.Session;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminFilter implements Filter {

    private final SecurityService securityService;

    public AdminFilter(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Session session = null;
        String requestUri = request.getRequestURI();
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String token = cookie.getValue();
                if (cookie.getName().equals("user-token")) {
                    session = securityService.getSession(token);
                }
                break;
            }
        }

        if (requestUri.equals("/products/*")) {
            if (session.getRole().equals(Role.ADMIN)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/login");
            }
            return;
        }
    }
}
