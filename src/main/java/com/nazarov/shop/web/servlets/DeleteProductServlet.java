package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {

    private ProductService productService;

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInformation = request.getPathInfo();
        int index = pathInformation.lastIndexOf("/");
        int id = Integer.valueOf(pathInformation.substring(index + 1, pathInformation.length()));
        productService.delete(id);
        response.sendRedirect("/products");
    }
}
