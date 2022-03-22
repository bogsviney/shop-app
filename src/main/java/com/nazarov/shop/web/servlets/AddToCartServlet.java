package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.CartService;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.service.security.SecurityService;
import com.nazarov.shop.service.security.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;

public class AddToCartServlet extends HttpServlet {

    private ProductService productService;
    private CartService cartService;
    private SecurityService securityService;

    public AddToCartServlet(ProductService productService, CartService cartService, SecurityService securityService) {
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
        Product productToAdd = productService.findById(id);
        cartService.addToCart(session.getCart(), productToAdd);
        response.sendRedirect("/products");
    }
}
