package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.CartService;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.service.security.SecurityService;
import com.nazarov.shop.service.security.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteFromCartServlet extends HttpServlet {

    private ProductService productService;
    private CartService cartService;
    private SecurityService securityService;

    public DeleteFromCartServlet(ProductService productService, CartService cartService, SecurityService securityService) {
        this.productService = productService;
        this.cartService = cartService;
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = securityService.getTokenFromCookies(request.getCookies());
        Session session = securityService.getSession(token);
        String pathInformation = request.getPathInfo();
        int index = pathInformation.lastIndexOf("/");
        int id = Integer.parseInt(pathInformation.substring(index + 1));
        Product productToDelete = productService.findById(id);
        cartService.removeFromCart(session.getCart(), productToDelete);
        response.sendRedirect("/products/cart");
    }
}
