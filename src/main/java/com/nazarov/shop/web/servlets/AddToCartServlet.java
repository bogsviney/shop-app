package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.CartService;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.service.security.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class AddToCartServlet extends HttpServlet {

    private ProductService productService;
    private  CartService cartService;

    public AddToCartServlet(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product productToAdd = getProductFromRequest(request);
        Session session = (Session) request.getAttribute("session");
        cartService.addToCart(session.getCart(),productToAdd);
        response.sendRedirect("/products");
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        return Product.builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .build();
    }
}
