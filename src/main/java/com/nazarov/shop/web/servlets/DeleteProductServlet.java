package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.service.security.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {


    private ProductService productService;
    public SecurityService securityService;

    public DeleteProductServlet(ProductService productService, SecurityService securityService) {
        this.productService = productService;
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.checkUserToken(securityService.getUserToken())) {
            String pathInformation = request.getPathInfo();
            int index = pathInformation.lastIndexOf("/");
            int id = Integer.parseInt(pathInformation.substring(index + 1));
            productService.delete(id);
            System.out.println("DELETE product with id: " + id);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/login");
        }
    }
}
