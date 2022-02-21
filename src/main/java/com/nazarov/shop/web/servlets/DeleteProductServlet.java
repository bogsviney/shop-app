package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DeleteProductServlet extends HttpServlet {
    private final List<String> userTokens;
    private ProductService productService;
    public SecurityAuthChecker securityAuthChecker = new SecurityAuthChecker();

    public DeleteProductServlet(ProductService productService, List<String> userTokens) {
        this.productService = productService;
        this.userTokens = userTokens;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityAuthChecker.checkUserAuthorization(request, userTokens)) {
            String pathInformation = request.getPathInfo();
            int index = pathInformation.lastIndexOf("/");
            int id = Integer.valueOf(pathInformation.substring(index + 1, pathInformation.length()));
            productService.delete(id);
            System.out.println("DELETE product with id: " + id);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/login");
        }
    }
}
