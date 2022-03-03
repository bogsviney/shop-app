package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.UserService;
import com.nazarov.shop.service.security.SecurityService;
import com.nazarov.shop.web.util.PageGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;


public class LoginServlet extends HttpServlet {

    private final SecurityService securityService;
    private final UserService userService;

    public LoginServlet(UserService userService, SecurityService securityService) {
        this.userService = userService;
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

        if (userService.checkUser(email, password)) {
            securityService.generateAndAddUserTokenToUserTokensList();
            System.out.println("USER TOKEN:" + securityService.getUserToken());
            Cookie cookie = new Cookie("user-token", securityService.getUserToken());
            response.addCookie(cookie);
            System.out.println("LOGGED IN! WELCOME!");
            response.sendRedirect("/products");
        } else {
            System.out.println("EMAIL OR PASSWORD INCORRECT");
            response.sendRedirect("/login");
        }
    }
}

