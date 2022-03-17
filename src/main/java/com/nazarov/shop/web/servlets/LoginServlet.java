package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.security.SecurityService;
import com.nazarov.shop.web.util.PageGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final SecurityService securityService;

    public LoginServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    PageGenerator pageGenerator = PageGenerator.instance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = pageGenerator.getPage("login.html");
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("EMAIL: " + email);
        System.out.println("PASSWORD: " + password);

        String token = securityService.login(email, password);

        if (token != null) {
            Cookie cookie = new Cookie("user-token", token);
            cookie.setMaxAge(900);
            response.addCookie(cookie);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/login");
        }
    }
}

