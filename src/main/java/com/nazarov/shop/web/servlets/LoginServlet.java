package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class LoginServlet extends HttpServlet {

    private final List<String> userTokens;
    private ProductService productService;

    public LoginServlet(List<String> userTokens) {
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
        userTokens.add(userToken);
        Cookie cookie = new Cookie("user-token", userToken);
        response.addCookie(cookie);
        response.sendRedirect("/products");
    }
}

