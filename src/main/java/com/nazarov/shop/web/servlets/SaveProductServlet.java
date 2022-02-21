package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.servlets.security.SecurityAuthChecker;
import com.nazarov.shop.web.util.PageGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SaveProductServlet extends HttpServlet {

    private ProductService productService;
    private List<String> userTokens;
    public SecurityAuthChecker securityAuthChecker = new SecurityAuthChecker();

    public SaveProductServlet(ProductService productService, List<String> userTokens) {
        this.productService = productService;
        this.userTokens = userTokens;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityAuthChecker.checkUserAuthorization(request, userTokens)) {
            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("products_save.html");
            response.getWriter().write(page);
        } else {
            response.sendRedirect("/login");
        }
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
