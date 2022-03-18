package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

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
        int id = Integer.parseInt(pathInformation.substring(index + 1));
        productService.delete(id);
        System.out.println("DELETE product with id: " + id);
        response.sendRedirect("/products");
    }
}
