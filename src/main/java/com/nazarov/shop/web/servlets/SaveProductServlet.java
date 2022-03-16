package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.service.security.SecurityService;
import com.nazarov.shop.web.util.PageGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

public class SaveProductServlet extends HttpServlet {

    private final ProductService productService;
    private final SecurityService securityService;

    public SaveProductServlet(ProductService productService, SecurityService securityService) {
        this.productService = productService;
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("products_save.html");
            response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = getProductFromRequest(request);
            productService.save(product);
            System.out.println("NEW PRODUCT ADDED");
            response.sendRedirect("/products");
        } catch (Exception e) {
            String errorMessage = "Product not added! Enter correct data into the fields";
            PageGenerator pageGenerator = PageGenerator.instance();
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = pageGenerator.getPage("products_save.html", parameters);
            response.getWriter().write(page);
        }
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        return Product.builder()
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .build();
    }
}
