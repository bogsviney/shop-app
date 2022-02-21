package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.service.UserService;
import com.nazarov.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoginServlet extends HttpServlet {

    private final List<String> userTokens;
    private ProductService productService;
    private UserService userService;

    public LoginServlet(UserService userService, List<String> userTokens) {
        this.userService = userService;
        this.userTokens = userTokens;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("login.html");
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userToken = UUID.randomUUID().toString();

        System.out.println("EMAIL: " + email);
        System.out.println("PASSWORD: " + password);
        System.out.println("USER TOKEN:" + userToken);

        if (userService.checkUser(email, password)) {
            userTokens.add(userToken);
            Cookie cookie = new Cookie("user-token", userToken);
            response.addCookie(cookie);
            System.out.println("LOGGED IN! WELLCOME!");
            response.sendRedirect("/products");
        } else {
            System.out.println("PASSWORD IS INCORRECT");
            response.sendRedirect("/login");
        }
    }
}

